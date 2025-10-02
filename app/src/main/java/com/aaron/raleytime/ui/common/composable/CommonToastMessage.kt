package com.aaron.raleytime.ui.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CommonToastMessage(
    content: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray.copy(0.6f))
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(8.dp),
            color = Color.Gray.copy(alpha = 0.8f),
            shadowElevation = 8.dp
        ) {
            Text(
                text = "Copied successfully: $content",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
}