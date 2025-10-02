package com.aaron.raleytime.ui.common.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CommonButton(
    buttonTitle: String,
    color: Color = Color.Blue.copy(0.4f),
    modifier:Modifier = Modifier.fillMaxWidth(),
    onClick: () -> Unit
) {
    Column {
        Button(
            modifier = modifier,
            onClick = { onClick.invoke() },
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(0.dp),
            interactionSource = remember {
                MutableInteractionSource()
            },
            colors = ButtonDefaults.buttonColors(containerColor = color)
        ) {
            CommonText(
                TextAttributes(
                    text = buttonTitle,
                    textAlign = TextAlign.Center,
                    textColor = Color.White,
                    textStyle = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                      16.dp
                    )
                )
            )

        }
    }

}
