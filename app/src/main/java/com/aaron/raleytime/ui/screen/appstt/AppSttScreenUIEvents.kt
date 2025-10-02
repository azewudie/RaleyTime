package com.aaron.raleytime.ui.screen.appstt

sealed class AppSttScreenUIEvents {
    data class StartListening(val language: String = "en-US") : AppSttScreenUIEvents()
    object StopListening : AppSttScreenUIEvents()
    data object OnCopyClicked : AppSttScreenUIEvents()
    data object OnUpDateToastMessageStatus : AppSttScreenUIEvents()

}