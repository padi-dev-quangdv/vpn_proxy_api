package com.midterm.securevpnproxy.presentation.auth.ui

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.LargeTextSemiBold
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.SmallTextRegular

@Composable
fun AuthHeaderUi(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    subtitle: String = stringResource(id = R.string.description_login),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(40.dp)
                .background(LocalColors.current.infoSurface)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
            )
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = title,
            style = LargeTextSemiBold,
            fontSize = 24.sp,
            color = LocalColors.current.neutral90,
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = subtitle,
            style = SmallTextRegular,
            color = LocalColors.current.neutral70
        )
    }
}