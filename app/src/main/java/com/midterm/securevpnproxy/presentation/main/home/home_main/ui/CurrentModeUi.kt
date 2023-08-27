package com.midterm.securevpnproxy.presentation.main.home.home_main.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.LargeTextBold
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.SmallTextRegular

@Composable
fun CurrentModeUi(
    title: String,
    subTitle: String,
    rightActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = LocalColors.current.infoMain,
    @DrawableRes icon: Int = R.drawable.ic_shield,
    rightActionText: String = stringResource(id = R.string.change),
    primaryColor: Color = LocalColors.current.infoMain,
    titleColor: Color = LocalColors.current.neutral90,
    subTitleColor: Color = LocalColors.current.neutral70,
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = primaryColor, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .background(color = iconBackgroundColor, shape = CircleShape)
                .size(40.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = title, style = LargeTextBold, color = titleColor)
            Text(text = subTitle, style = SmallTextRegular, color = subTitleColor)
        }
        Text(text = rightActionText, style = SmallTextRegular, color = primaryColor)
        Image(
            modifier = Modifier.padding(start = 10.dp),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Next",
            colorFilter = ColorFilter.tint(color = primaryColor)
        )
    }
}

@Preview
@Composable
private fun PreviewCurrentModeUi() {
    CurrentModeUi(
        title = "Family filter",
        subTitle = "active",
        rightActionClicked = {},
        modifier = Modifier.fillMaxWidth()
    )
}