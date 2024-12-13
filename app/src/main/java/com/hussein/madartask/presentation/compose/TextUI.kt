package com.hussein.madartask.presentation.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextUI(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 12.sp,
    colorText: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = TextStyle.Default,
    textAlign :TextAlign = TextAlign.Unspecified,
    fontFamily: FontFamily = fontFamilyName,
    fontWeight: FontWeight =FontWeight.Normal
) {
    val textStyleBody by remember { mutableStateOf(textStyle.copy(fontSize = textSize)) }

    Text(
        modifier = modifier,
        text = text,
        fontSize = textSize,
        color = colorText,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis, // Handle overflow with ellipsis or clip, etc.
        style = textStyleBody,
        textDecoration = textStyle.textDecoration,
        fontWeight = fontWeight,
        fontFamily =  fontFamily,
        textAlign = textAlign,
    )
    
}