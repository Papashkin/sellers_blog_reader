package com.antsfamily.sellersblogreader.ui.common

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.core.text.HtmlCompat

@Composable
fun HtmlText(
    text: String,
    modifier: Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Text(
        text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
        style = style,
        modifier = modifier,
        fontSize = fontSize
    )
}
