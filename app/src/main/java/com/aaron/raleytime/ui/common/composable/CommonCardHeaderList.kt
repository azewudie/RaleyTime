package com.aaron.raleytime.ui.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CommonCardHeaderList(
    header: String,
    detail: String,
) {
    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = Color.Gray.copy(0.7f),
            ),
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
        ) {
            CommonText(
                TextAttributes(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    text = header,
                    textStyle = MaterialTheme.typography.headlineMedium,
                    textColor = Color.White,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            CommonText(
                TextAttributes(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    text = detail,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    textColor = Color.White,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        }
    }

}