package com.midterm.securevpnproxy.presentation.main.home.home_main.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.midterm.securevpnproxy.base.compose.LargeTextBold

@Composable
fun VpnProtectUi(
    modifier: Modifier = Modifier,
    type: VpnToggleState,
) {
    Text(
        modifier = modifier,
        text = stringResource(id = type.text),
        style = LargeTextBold,
        color = type.circle1Color()
    )
}

