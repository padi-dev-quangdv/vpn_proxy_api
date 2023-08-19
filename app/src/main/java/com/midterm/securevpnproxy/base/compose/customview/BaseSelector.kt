package com.midterm.securevpnproxy.base.compose.customview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.LabelSmall
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.ParagraphMedium
import com.midterm.securevpnproxy.base.compose.noRippleClickable

@Composable
fun BaseSelector(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
    @DrawableRes endIconRes: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = LocalColors.current.whiteAlpha04,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable { onClick.invoke() }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content()
            Image(
                painter = painterResource(id = endIconRes),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun NormalSelector(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalColors.current.lightGrey03,
    hint: String? = null,
    hintColor: Color = LocalColors.current.lightGrey09,
    @DrawableRes endIconRes: Int,
    onClick: () -> Unit,
) {
    BaseSelector(
        modifier = modifier,
        endIconRes = endIconRes,
        onClick = onClick,
        content = {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (hint != null) {
                    Text(
                        text = hint,
                        style = LabelSmall,
                        color = hintColor,
                    )
                }
                Text(
                    text = text,
                    style = ParagraphMedium,
                    color = textColor,
                )
            }
        }
    )
}

@Composable
fun DropdownSelector(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalColors.current.lightGrey03,
    hint: String? = null,
    hintColor: Color = LocalColors.current.lightGrey09,
    @DrawableRes endIconRes: Int = R.drawable.ic_back,
    onClick: () -> Unit,
) {
    NormalSelector(
        modifier = modifier,
        text = text,
        textColor = textColor,
        hint = hint,
        hintColor = hintColor,
        endIconRes = endIconRes,
        onClick = onClick
    )
}