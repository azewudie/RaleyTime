package com.aaron.raleytime.repository

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.aaron.raleytime.data.local.speechtotexttextospeech.SpeechToTextState
import com.aaron.raleytime.data.service.SpeechRecognitionRepository
import com.aaron.raleytime.utlits.constant.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SpeechRecognitionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SpeechRecognitionRepository {

    private val _speechState = MutableStateFlow(SpeechToTextState())
    override fun observeSpeechState(): StateFlow<SpeechToTextState> = _speechState.asStateFlow()

    private var recognizer: SpeechRecognizer? = null

    private val recognitionListener = object : RecognitionListener {
        override fun onBeginningOfSpeech() = Unit

        override fun onBufferReceived(buffer: ByteArray?) = Unit

        override fun onEndOfSpeech() {
            _speechState.update { it.copy(isSpeaking = false) }
        }

        override fun onError(error: Int) {
            if (error == SpeechRecognizer.ERROR_CLIENT) return

            _speechState.update {
                it.copy(
                    isSpeaking = false,
                    error = getErrorMessage(error)
                )
            }
        }

        override fun onEvent(eventType: Int, params: Bundle?) = Unit

        override fun onPartialResults(partialResults: Bundle?) = Unit

        override fun onReadyForSpeech(params: Bundle?) {
            _speechState.update { it.copy(error = AppConstants.EMPTY_STRING) }
        }

        override fun onResults(results: Bundle?) {
            val spokenText = results
                ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                ?.getOrNull(0)

            _speechState.update {
                it.copy(
                    spokenText = spokenText ?: "",
                    isSpeaking = false
                )
            }
        }

        override fun onRmsChanged(rmsdB: Float) = Unit
    }

    override fun startListening(language: String) {
        /**
         * Reset state
         */
        _speechState.update { SpeechToTextState(isSpeaking = true) }
        /**
         * Check if recognizer is available
         */

        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            _speechState.update {
                it.copy(
                    isSpeaking = false,
                    error = "Speech recognition is not available on this device"
                )
            }
            return
        }

        /**
         * Clean up existing recognizer
         */
        recognizer?.destroy()

        /**
         * Create new recognizer
         */
        recognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(recognitionListener)
        }
        /**
         * Start listening
         */

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
        }

        recognizer?.startListening(intent)
    }

    override fun stopListening() {
        recognizer?.stopListening()
        _speechState.update { it.copy(isSpeaking = false) }
    }

    override fun cleanup() {
        recognizer?.destroy()
        recognizer = null
    }

    private fun getErrorMessage(error: Int): String {
        return when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Client side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No speech match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognition service busy"
            SpeechRecognizer.ERROR_SERVER -> "Server error"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            else -> ""
        }
    }
}