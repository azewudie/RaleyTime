package com.aaron.raleytime.data.service

import com.aaron.raleytime.data.local.speechtotexttextospeech.TextToSpeechState
import kotlinx.coroutines.flow.StateFlow

interface TextToSpeechRepository {
    fun speak(text: String)
    fun pause()
    fun resume()
    fun stop()
    fun setSpeechRate(rate: Float)
    fun setPitch(pitch: Float)
    fun setLanguage(languageCode: String)
    fun observeTtsState(): StateFlow<TextToSpeechState>
    fun cleanup()
}
