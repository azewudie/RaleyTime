package com.aaron.raleytime.ui.screen.appstt

import com.aaron.raleytime.data.local.speechtotexttextospeech.SpeechToTextState
import com.aaron.raleytime.utlits.constant.AppConstants

data class AppSttScreenUIStates(
    val appSttScreenHeaderTitle: String = AppConstants.EMPTY_STRING,
    val appSttScreenBodyText: String = AppConstants.EMPTY_STRING,
    val isButtonClicked: Boolean = false,
    val speakingText:String = AppConstants.EMPTY_STRING,
    val speechText: SpeechToTextState = SpeechToTextState(),
    var isCopyClicked: Boolean = false,

    )