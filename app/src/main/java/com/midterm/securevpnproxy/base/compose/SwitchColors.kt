package com.midterm.securevpnproxy.base.compose

import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import com.midterm.securevpnproxy.base.compose.LocalColors

object SwitchColors {
    @Composable
    fun colors(): SwitchColors {
        return SwitchDefaults.colors(
            checkedThumbColor = LocalColors.current.white,
            checkedTrackColor = LocalColors.current.infoMain,
            uncheckedThumbColor = LocalColors.current.white,
            checkedTrackAlpha = 1f,
            uncheckedTrackColor = LocalColors.current.neutral60,
            uncheckedTrackAlpha = 1f,
            disabledCheckedThumbColor = LocalColors.current.white,
            disabledCheckedTrackColor = LocalColors.current.neutral60,
            disabledUncheckedThumbColor = LocalColors.current.white,
            disabledUncheckedTrackColor = LocalColors.current.neutral60
        )
    }
}