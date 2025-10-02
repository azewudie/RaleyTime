package com.aaron.raleytime.ui.screen.appstt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aaron.raleytime.ui.common.composable.CommonToastMessage

@Composable
fun AppSttScreen(
    screenState: State<AppSttScreenUIStates>,
    onEvent: (AppSttScreenUIEvents) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(0.6f))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (screenState.value.isCopyClicked) {
            CommonToastMessage(content = screenState.value.speechText.spokenText)
            onEvent.invoke(AppSttScreenUIEvents.OnUpDateToastMessageStatus)

        }
        /**
         * Header Section Of The Screen
         */

        Text(
            text = screenState.value.appSttScreenHeaderTitle,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 32.dp),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Microphone Button Section Of The Screen
         */
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                onClick = {
                    if (screenState.value.speechText.isSpeaking) {
                        onEvent(AppSttScreenUIEvents.StopListening)
                    } else {
                        onEvent(AppSttScreenUIEvents.StartListening())
                    }
                },
                modifier = Modifier.size(80.dp),
                containerColor = if (screenState.value.speechText.isSpeaking) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.primary
                }
            ) {
                Icon(
                    imageVector = if (screenState.value.speechText.isSpeaking) {
                        Icons.Default.Stop
                    } else {
                        Icons.Default.Mic
                    },
                    contentDescription = if (screenState.value.speechText.isSpeaking) {
                        "Stop listening"
                    } else {
                        "Start listening"
                    },
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        /**
         * Status Text Section Of The Screen
         */

        Text(
            text = when {
                screenState.value.speechText.isSpeaking -> "Listening..."
                screenState.value.speechText.error.isNotEmpty() -> "Error"
                screenState.value.speechText.spokenText.isNotEmpty() -> "Tap to speak again"
                else -> "Tap the microphone to start"
            },
            style = MaterialTheme.typography.bodyLarge,
            color = when {
                screenState.value.speechText.isSpeaking -> MaterialTheme.colorScheme.primary
                screenState.value.speechText.error.isNotEmpty() -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.onSurfaceVariant
            },
            textAlign = TextAlign.Center
        )

        /**
         *  Error Message Section Of The Screen
         */

        if (screenState.value.speechText.error.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = screenState.value.speechText.error.let {
                            "Error: $it"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }

        /**
         * Spoken Text Display section of the screen
         */
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(0.6f))
                .weight(1.5f),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(0.6f))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transcription",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    if (screenState.value.speechText.spokenText.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                onEvent.invoke(AppSttScreenUIEvents.OnCopyClicked)

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy text",
                            )
                        }
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = if (screenState.value.speechText.spokenText.isEmpty()) {
                        Alignment.Center
                    } else {
                        Alignment.TopStart
                    }
                ) {
                    if (screenState.value.speechText.spokenText.isEmpty()) {
                        Text(
                            text = "Your transcription will appear here",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        SelectionContainer {
                            Text(
                                text = screenState.value.speechText.spokenText,
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 28.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}