package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LabelLarge
import com.midterm.securevpnproxy.base.compose.LabelMedium
import com.midterm.securevpnproxy.base.compose.LabelSmall

/**
 * Normal Buttons
 */
@Composable
fun ExtraLargeButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    Button(
        onClick = onClick,
        colors = color,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = LabelLarge, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun LargeButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = color,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = LabelMedium, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = color,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        prefix()
        Text(text = text, style = LabelMedium, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    color: ButtonColors,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = color,
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
    ) {
        prefix()
        Text(text = text, style = LabelSmall, textAlign = TextAlign.Center)
        postfix()
    }
}

/**
 * Text Buttons
 */

@Composable
fun ExtraLargeTextButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = color,
        border = border,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = LabelLarge, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun LargeTextButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = color,
        border = border,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
    ) {
        prefix()
        Text(text = text, style = LabelMedium, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun MediumTextButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColors,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = color,
        shape = MaterialTheme.shapes.medium,
        border = border,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        prefix()
        Text(text = text, style = LabelMedium, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun SmallTextButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    color: ButtonColors,
    border: BorderStroke? = null,
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        enabled = enabled,
        onClick = onClick,
        colors = color,
        shape = RoundedCornerShape(4.dp),
        border = border,
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
    ) {
        prefix()
        Text(text = text, style = LabelSmall, textAlign = TextAlign.Center)
        postfix()
    }
}

@Composable
fun ExtraLargeTransparentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 14.dp, horizontal = 16.dp)
    ) {
        prefix()
        content()
        postfix()
    }
}

@Composable
fun LargeTransparentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
    ) {
        prefix()
        content()
        postfix()
    }
}

@Composable
fun MediumTransparentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        prefix()
        content()
        postfix()
    }
}

@Composable
fun SmallTransparentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp)
    ) {
        prefix()
        content()
        postfix()
    }
}

@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: ButtonColors,
    content: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit,
    prefix: @Composable RowScope.() -> Unit = {},
    postfix: @Composable RowScope.() -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = color,
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        prefix()
        content()
        postfix()
    }
}