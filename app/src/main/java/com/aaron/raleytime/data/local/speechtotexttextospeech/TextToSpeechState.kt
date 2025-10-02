package com.aaron.raleytime.data.local.speechtotexttextospeech

data class TextToSpeechState(
    val text: String = "",
    val isSpeaking: Boolean = false,
    val isPaused: Boolean = false,
    val progress: Float = 0f,
    val error: String? = null,
    val selectedLanguage: String = "en-US",
    val speechRate: Float = 1.0f,
    val pitch: Float = 1.0f
)

