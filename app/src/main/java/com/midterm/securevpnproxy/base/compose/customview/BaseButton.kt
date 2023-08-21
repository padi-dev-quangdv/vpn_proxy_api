package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LargeTextSemiBold
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.MediumTextSemiBold

/**
 * Base Buttons
 */
@Composable
fun SmallSolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    center: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = color,
        border = null,
        shape = RoundedCornerShape(size = 56.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = MediumTextSemiBold, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun SmallOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    outlinedColor: ButtonColors,
    enabled: Boolean = true,
    borderColor: Color = LocalColors.current.infoMain,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = outlinedColor,
        border = BorderStroke(width = 1.dp, color = borderColor),
        shape = RoundedCornerShape(size = 56.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = MediumTextSemiBold, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun LargeSolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = color,
        border = null,
        shape = RoundedCornerShape(size = 41.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = LargeTextSemiBold, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun LargeOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    outlinedColor: ButtonColors,
    enabled: Boolean = true,
    borderColor: Color = LocalColors.current.infoMain,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = outlinedColor,
        border = BorderStroke(width = 1.dp, color = borderColor),
        shape = RoundedCornerShape(size = 41.dp),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = LargeTextSemiBold, textAlign = TextAlign.Center)
        postfix()
    }
}
