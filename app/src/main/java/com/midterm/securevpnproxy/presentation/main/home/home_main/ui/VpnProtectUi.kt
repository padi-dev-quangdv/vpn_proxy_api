package com.midterm.securevpnproxy.presentation.main.home.home_main.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.LocalColors

@Composable
fun VpnToggleUi(
    modifier: Modifier = Modifier,
    colorSet: VpnToggleState,
) {
    Box(modifier = modifier.size(214.dp)) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(color = colorSet.circle5Color(), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .matchParentSize()
                .background(colorSet.circle4Color(), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .padding(32.dp)
                .matchParentSize()
                .background(colorSet.circle3Color(), shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .padding(48.dp)
                .matchParentSize()
                .shadow(
                    3.dp,
                    shape = CircleShape,
                    ambientColor = colorSet.circle2Color(),
                    spotColor = colorSet.circle2Color()
                )
        )
        Box(
            modifier = Modifier
                .padding((48 + 35).dp)
                .matchParentSize()
                .background(colorSet.circle1Color(), shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_power),
                contentDescription = "Power",
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }
}


interface VpnToggleState {
    @Composable
    fun circle1Color(): Color

    @Composable
    fun circle2Color(): Color

    @Composable
    fun circle3Color(): Color

    @Composable
    fun circle4Color(): Color

    @Composable
    fun circle5Color(): Color

    @get:StringRes
    val text: Int

    object Inactive : VpnToggleState {
        @Composable
        override fun circle1Color(): Color = LocalColors.current.neutral50

        @Composable
        override fun circle2Color(): Color = LocalColors.current.white

        @Composable
        override fun circle3Color(): Color = LocalColors.current.white

        @Composable
        override fun circle4Color(): Color = LocalColors.current.neutral60.copy(alpha = 0.05f)

        @Composable
        override fun circle5Color(): Color = LocalColors.current.neutral50.copy(alpha = 0.05f)

        override val text: Int
            get() = R.string.not_protected_status
    }

    object Active : VpnToggleState {
        @Composable
        override fun circle1Color(): Color = LocalColors.current.infoMain

        @Composable
        override fun circle2Color(): Color = LocalColors.current.white

        @Composable
        override fun circle3Color(): Color = LocalColors.current.white

        @Composable
        override fun circle4Color(): Color = LocalColors.current.infoMain.copy(alpha = 0.05f)

        @Composable
        override fun circle5Color(): Color = LocalColors.current.infoBorder.copy(alpha = 0.05f)

        override val text: Int
            get() = R.string.protected_status
    }
}