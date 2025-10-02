package com.aaron.raleytime.ui.screen.apptts

sealed class AppTtsScreenUIEvents {
    data class OnTextChanged(val text: String) : AppTtsScreenUIEvents()
    object Speak : AppTtsScreenUIEvents()
    object Pause : AppTtsScreenUIEvents()
    object Resume : AppTtsScreenUIEvents()
    object Stop : AppTtsScreenUIEvents()
    data class OnSpeechRateChanged(val rate: Float) : AppTtsScreenUIEvents()
    data class OnPitchChanged(val pitch: Float) : AppTtsScreenUIEvents()

}