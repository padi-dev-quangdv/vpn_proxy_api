package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.midterm.securevpnproxy.base.compose.appFontFamily

@Composable
fun AppClickableText(fullText: FullTextOption, vararg options: HyperTextOption) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = fullText.color,
                fontFamily = appFontFamily,
                fontWeight = fullText.fontWeight,
                fontSize = fullText.fontSize
            )
        ) {
            append(fullText.text)
        }
        options.forEach { option ->
            val startOffset = fullText.text.indexOf(option.hypertext)
            val endOffset = startOffset + option.hypertext.length
            addStringAnnotation(
                tag = option.tag,
                annotation = "",
                start = startOffset,
                end = endOffset
            )
            addStyle(
                style = SpanStyle(
                    color = option.color,
                    fontFamily = appFontFamily,
                    fontWeight = option.fontWeight,
                    fontSize = option.fontSize
                ),
                start = startOffset,
                end = endOffset,
            )
        }
    }
    ClickableText(text = annotatedText) { offset ->
        val annotations = annotatedText.getStringAnnotations(start = offset, end = offset)
        val tags = annotations.map { it.tag }
        options.filter { tags.contains(it.tag) }.forEach { it.onClicked.invoke() }
    }
}

data class FullTextOption(
    val text: String,
    val color: Color,
    val fontWeight: FontWeight,
    val fontSize: TextUnit
)

data class HyperTextOption(
    val tag: String,
    val hypertext: String,
    val color: Color,
    val fontWeight: FontWeight,
    val fontSize: TextUnit,
    val onClicked: () -> Unit = {},
)