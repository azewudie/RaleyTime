package com.aaron.raleytime.repository

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import com.aaron.raleytime.data.local.speechtotexttextospeech.TextToSpeechState
import com.aaron.raleytime.data.service.TextToSpeechRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class TextToSpeechRepositoryImpl(
    private val context: Context
) : TextToSpeechRepository, TextToSpeech.OnInitListener {

    private val _ttsState = MutableStateFlow(TextToSpeechState())
    override fun observeTtsState(): StateFlow<TextToSpeechState> = _ttsState.asStateFlow()

    private var textToSpeech: TextToSpeech? = null
    private var isInitialized = false

    init {
        initializeTts()
    }

    private fun initializeTts() {
        textToSpeech = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            textToSpeech?.let { tts ->
                // Set default language
                val result = tts.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    _ttsState.update {
                        it.copy(error = "Language not supported")
                    }
                }
                /**
                 * Set utterance progress listener
                 */

                tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        _ttsState.update {
                            it.copy(
                                isSpeaking = true,
                                isPaused = false,
                                error = null
                            )
                        }
                    }

                    override fun onDone(utteranceId: String?) {
                        _ttsState.update {
                            it.copy(
                                isSpeaking = false,
                                isPaused = false,
                                progress = 0f
                            )
                        }
                    }

                    override fun onError(utteranceId: String?) {
                        _ttsState.update {
                            it.copy(
                                isSpeaking = false,
                                isPaused = false,
                                error = "Speech synthesis failed"
                            )
                        }
                    }

                    override fun onRangeStart(
                        utteranceId: String?,
                        start: Int,
                        end: Int,
                        frame: Int
                    ) {
                        val currentText = _ttsState.value.text
                        if (currentText.isNotEmpty()) {
                            val progress = (end.toFloat() / currentText.length) * 100f
                            _ttsState.update {
                                it.copy(progress = progress.coerceIn(0f, 100f))
                            }
                        }
                    }
                })
            }
        } else {
            _ttsState.update {
                it.copy(error = "Text-to-Speech initialization failed")
            }
        }
    }

    override fun speak(text: String) {
        if (!isInitialized) {
            _ttsState.update {
                it.copy(error = "Text-to-Speech not initialized")
            }
            return
        }

        if (text.isEmpty()) {
            _ttsState.update {
                it.copy(error = "Please enter text to speak")
            }
            return
        }

        _ttsState.update { it.copy(text = text, error = null) }

        val params = Bundle().apply {
            putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "tts_utterance_id")
        }

        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, params, "tts_utterance_id")
    }

    override fun pause() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textToSpeech?.stop()
            _ttsState.update {
                it.copy(
                    isSpeaking = false,
                    isPaused = true
                )
            }
        } else {
            stop()
        }
    }

    override fun resume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val currentText = _ttsState.value.text
            if (currentText.isNotEmpty()) {
                speak(currentText)
            }
        }
    }

    override fun stop() {
        textToSpeech?.stop()
        _ttsState.update {
            it.copy(
                isSpeaking = false,
                isPaused = false,
                progress = 0f
            )
        }
    }

    override fun setSpeechRate(rate: Float) {
        textToSpeech?.setSpeechRate(rate)
        _ttsState.update { it.copy(speechRate = rate) }
    }

    override fun setPitch(pitch: Float) {
        textToSpeech?.setPitch(pitch)
        _ttsState.update { it.copy(pitch = pitch) }
    }

    override fun setLanguage(languageCode: String) {
        val locale = Locale.forLanguageTag(languageCode)
        val result = textToSpeech?.setLanguage(locale)

        if (result == TextToSpeech.LANG_MISSING_DATA ||
            result == TextToSpeech.LANG_NOT_SUPPORTED) {
            _ttsState.update {
                it.copy(error = "Language $languageCode not supported")
            }
        } else {
            _ttsState.update {
                it.copy(
                    selectedLanguage = languageCode,
                    error = null
                )
            }
        }
    }


    override fun cleanup() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
        isInitialized = false
    }
}
