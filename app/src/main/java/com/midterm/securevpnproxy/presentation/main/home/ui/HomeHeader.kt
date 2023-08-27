package com.midterm.securevpnproxy.presentation.main.home.ui

import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.LargeTextBold
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.customview.BaseHeader

@Composable
fun HomeHeader(
    @DrawableRes iconStart: Int,
    @DrawableRes iconEnd: Int,
    onStartItemClicked: () -> Unit,
    onEndItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
    iconColor: Color = LocalColors.current.neutral100,
    centerTitle: String = stringResource(id = R.string.app_name),
    centerTitleColor: Color = LocalColors.current.neutral90,
) {
    BaseHeader(
        modifier = modifier,
        startContent = {
            Image(
                painter = painterResource(id = iconStart),
                colorFilter = ColorFilter.tint(color = iconColor),
                contentDescription = "Menu",
                modifier = Modifier.clickable { onStartItemClicked.invoke() }
            )
        },
        centerContent = {
            Text(
                text = centerTitle,
                style = LargeTextBold,
                color = centerTitleColor
            )
        },
        endContent = {
            Image(
                painter = painterResource(id = iconEnd),
                colorFilter = ColorFilter.tint(color = iconColor),
                contentDescription = "Menu",
                modifier = Modifier.clickable { onEndItemClicked.invoke() }
            )
        })
}