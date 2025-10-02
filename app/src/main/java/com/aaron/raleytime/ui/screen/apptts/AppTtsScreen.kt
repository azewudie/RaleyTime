package com.aaron.raleytime.ui.screen.apptts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppTtsScreen(
    screenState: State<AppTtsScreenUIStates>,
    onEvent: (AppTtsScreenUIEvents) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.6f))
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        /**
         * Header Section Of The Screen
         */
        Text(
            text = screenState.value.appTtsScreenHeaderTitle,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))
        /**
         * Text Input Field Section Of The Screen
         */

        OutlinedTextField(
            value = screenState.value.appTtsInputText,
            onValueChange = { onEvent(AppTtsScreenUIEvents.OnTextChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            label = {
                Text(
                    text = "Enter text to speak",
                    color = Color.White
                )
            },
            placeholder = {
                Text(
                    text = "Type your text here...",
                    color = Color.White
                )
            },
            enabled = !screenState.value.appTtsState.isSpeaking,
            maxLines = 8,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            )
        )
        /**
         * Error Message Section Of The Screen
         */

        if (screenState.value.appTtsState.error != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = screenState.value.appTtsState.error!!,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
        /**
         * Progress Bar Section Of The Screen
         */

        if (screenState.value.appTtsState.isSpeaking) {
            Column(modifier = Modifier.fillMaxWidth()) {
                LinearProgressIndicator(
                    progress = screenState.value.appTtsState.progress / 100f,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Speaking... ${screenState.value.appTtsState.progress.toInt()}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        /**
         * Control Buttons Section Of The Screen
         */

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            /**
             * Speak Button Section Of The Screen
             */
            Button(
                onClick = {
                    when {
                        screenState.value.appTtsState.isSpeaking -> onEvent(AppTtsScreenUIEvents.Stop)
                        screenState.value.appTtsState.isPaused -> onEvent(AppTtsScreenUIEvents.Resume)
                        else -> onEvent(AppTtsScreenUIEvents.Speak)
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = screenState.value.appTtsInputText.isNotEmpty() || screenState.value.appTtsState.isSpeaking
            ) {
                Icon(
                    imageVector = when {
                        screenState.value.appTtsState.isSpeaking -> Icons.Default.Stop
                        screenState.value.appTtsState.isPaused -> Icons.Default.PlayArrow
                        else -> Icons.Default.VolumeUp
                    },
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    when {
                        screenState.value.appTtsState.isSpeaking -> "Stop"
                        screenState.value.appTtsState.isPaused -> "Resume"
                        else -> "Speak"
                    }
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        /**
         * Speech Slider Section Of The Screen
         */

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Speed",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = "${(screenState.value.appTtsState.speechRate * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Slider(
                value = screenState.value.appTtsState.speechRate,
                onValueChange = { onEvent(AppTtsScreenUIEvents.OnSpeechRateChanged(it)) },
                valueRange = 0.5f..2.0f,
                steps = 14
            )
        }

        /**
         * Pitch Slider Section Of The Screen
         */

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Pitch",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Text(
                    text = "${(screenState.value.appTtsState.pitch * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Slider(
                value = screenState.value.appTtsState.pitch,
                onValueChange = { onEvent(AppTtsScreenUIEvents.OnPitchChanged(it)) },
                valueRange = 0.5f..2.0f,
                steps = 14
            )
        }
    }
}
