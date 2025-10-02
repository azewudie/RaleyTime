package com.aaron.raleytime.data.service

import com.aaron.raleytime.data.local.speechtotexttextospeech.SpeechToTextState
import kotlinx.coroutines.flow.StateFlow

interface SpeechRecognitionRepository {
    fun startListening(language: String)
    fun stopListening()
    fun observeSpeechState(): StateFlow<SpeechToTextState>
    fun cleanup()
}