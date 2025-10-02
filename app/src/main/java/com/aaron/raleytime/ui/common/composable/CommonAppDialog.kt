package com.aaron.raleytime.ui.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.aaron.raleytime.utlits.constant.AppConstants

@Composable
fun CommonAppDialog(
    showDialog: MutableState<Boolean>,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    content: String = AppConstants.EMPTY_STRING,
    buttonLabel: String = AppConstants.EMPTY_STRING
) {
    if (showDialog.value) {
        Column(
            modifier = Modifier.background(Color.Gray.copy(0.6f))
        ) {
            Dialog(
                onDismissRequest = {
                    onDismiss()
                    showDialog.value = false
                },
                properties = DialogProperties(
                    usePlatformDefaultWidth = true,
                    dismissOnClickOutside = true,
                    dismissOnBackPress = false
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1.0f)
                        .wrapContentHeight()
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color.Gray.copy(0.6f))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CommonText(
                            TextAttributes(
                                text = content,
                                textColor = Color.White,
                                textStyle = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        )

                        CommonButton(
                            buttonTitle = buttonLabel
                        ) {
                            onConfirm()
                            showDialog.value = false
                        }

                    }

                }
            }
        }
    }
}