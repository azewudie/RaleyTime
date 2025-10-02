package com.aaron.raleytime.ui.screen.appraleytime

import com.aaron.raleytime.utlits.constant.AppConstants

data class AppRaleyTimeScreenUIStates(
    val appRaleyTimeScreenHeaderTitle:String = AppConstants.EMPTY_STRING,
    val appRaleyTimeScreenBodyText:String = AppConstants.EMPTY_STRING,
    val showAppDialogSummery:Boolean = false,
    val appDialogSummeryBodyText:String = AppConstants.EMPTY_STRING,
    val appDialogSummeryTitle:String = AppConstants.EMPTY_STRING,
    val appDialogSummeryButtonLabel:String = AppConstants.EMPTY_STRING,


)