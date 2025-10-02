package com.aaron.raleytime.ui.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CommonText(
    textAttributes: TextAttributes = TextAttributes()
) {
    Column {
        Text(
            text = textAttributes.text,
            color = textAttributes.textColor,
            style = textAttributes.textStyle,
            modifier = textAttributes.modifier,
            textAlign = textAttributes.textAlign,
            textDecoration = textAttributes.textDecoration,
            maxLines = textAttributes.maxLines,
            overflow = textAttributes.overflow

        )
    }
}
