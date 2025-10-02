package com.aaron.raleytime.ui.screen.appraleytime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aaron.raleytime.ui.common.composable.CommonAppDialog
import com.aaron.raleytime.ui.common.composable.CommonCardHeaderList
import com.aaron.raleytime.ui.common.composable.CommonText
import com.aaron.raleytime.ui.common.composable.TextAttributes

@Composable
fun AppRaleyTimeScreen(
    screenState: State<AppRaleyTimeScreenUIStates>,
    onEvent: (AppRaleyTimeScreenUIEvents) -> Unit

) {
    LaunchedEffect(Unit) {
        onEvent.invoke(AppRaleyTimeScreenUIEvents.OnClickOk)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.6f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /**
         * App Dialog Section Of The Screen
         */
        if (screenState.value.showAppDialogSummery.not()) {
            CommonAppDialog(
                showDialog = remember { mutableStateOf(screenState.value.showAppDialogSummery.not()) },
                content = screenState.value.appDialogSummeryBodyText,
                buttonLabel = screenState.value.appDialogSummeryButtonLabel,
                onDismiss = {
                    onEvent.invoke(AppRaleyTimeScreenUIEvents.OnClickOk)
                },
                onConfirm = {
                    onEvent.invoke(AppRaleyTimeScreenUIEvents.OnClickOk)
                }
            )
        }

        Column(
            modifier = Modifier
                .imePadding()
                .consumeWindowInsets(androidx.compose.foundation.layout.WindowInsets.systemBars)
                .verticalScroll(rememberScrollState(0))
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1.0f, false),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * Header Section Of The Screen
             */
            CommonText(
                TextAttributes(
                    text = screenState.value.appRaleyTimeScreenHeaderTitle,
                    textColor = Color.White,
                    textStyle = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            )
            /**
             * Body Section Of The Screen
             */
            CommonCardHeaderList(
                header = "Raley",
                detail = screenState.value.appRaleyTimeScreenBodyText,
            )
        }
    }
}