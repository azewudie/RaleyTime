package com.aaron.raleytime.ui.common.composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

data class TextAttributes(
    var text:String = "",
    var textColor: Color = Color.Unspecified,
    var textStyle:TextStyle = TextStyle.Default,
    var modifier:Modifier = Modifier,
    var textAlign:TextAlign = TextAlign.Start,
    var textDecoration: TextDecoration = TextDecoration.None,
    var overflow: TextOverflow = TextOverflow.Clip,
    var maxLines:Int = Int.MAX_VALUE,
    var fontSize:TextUnit? = null
)