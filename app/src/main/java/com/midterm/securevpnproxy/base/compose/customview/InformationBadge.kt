package com.midterm.securevpnproxy.base.compose.customview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.AbsLightOrangeAlpha03
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.ParagraphSmall

@Composable
fun InformationBadge(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    radius: Dp = 8.dp,
    spaceBetweenContents: Dp = 8.dp,
    padding: Dp = 16.dp,
    startContent: @Composable RowScope.() -> Unit,
    endContent: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier.background(
            color = backgroundColor,
            shape = RoundedCornerShape(radius)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spaceBetweenContents),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(padding)
        ) {
            startContent()
            endContent()
        }
    }
}

@Composable
fun IconTextInformationBadge(
    modifier: Modifier = Modifier,
    textColor: Color,
    backgroundColor: Color,
    text: String,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = ParagraphSmall,
    @DrawableRes iconRes: Int,
) {
    InformationBadge(
        modifier = modifier,
        backgroundColor = backgroundColor,
        startContent = {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(iconSize)
            )
        },
        endContent = {
            Text(text = text, style = textStyle, color = textColor)
        })
}

@Composable
fun WarningInformationBadge(
    modifier: Modifier = Modifier,
    text: String,
) {
    IconTextInformationBadge(
        modifier = modifier,
        textColor = LocalColors.current.orange05,
        backgroundColor = AbsLightOrangeAlpha03,
        text = text,
        iconRes = R.drawable.ic_logo
    )
}