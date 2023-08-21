package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.MediumTextRegular
import com.midterm.securevpnproxy.base.compose.SmallTextRegular

@Composable
fun AppTextTooltip(tooltipText: String, paddingValues: PaddingValues, imageSize: Dp = 24.dp) {
    TooltipPopup(
        backgroundColor = LocalColors.current.neutral70,
        arrowColor = LocalColors.current.neutral70,
        requesterView = {
            Image(
                modifier = it
                    .padding(paddingValues)
                    .size(imageSize),
                painter = painterResource(id = R.drawable.ic_information),
                colorFilter = ColorFilter.tint(color = LocalColors.current.neutral70),
                contentDescription = "Help",
            )
        }, tooltipContent = {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                text = tooltipText,
                style = SmallTextRegular,
                color = LocalColors.current.neutral90
            )
        })
}