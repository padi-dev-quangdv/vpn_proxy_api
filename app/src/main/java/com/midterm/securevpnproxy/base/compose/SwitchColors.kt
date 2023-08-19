package com.midterm.securevpnproxy.base.compose

import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import com.midterm.securevpnproxy.base.compose.LocalColors

object SwitchColors {
    @Composable
    fun colors(): SwitchColors {
        return SwitchDefaults.colors(
            checkedThumbColor = AbsWhite,
            checkedTrackColor = AbsBlue07,
            uncheckedThumbColor = AbsBlue07,
            checkedTrackAlpha = 1f,
            uncheckedTrackColor = LocalColors.current.white,
            uncheckedTrackAlpha = 0.08f,
            disabledCheckedThumbColor = LocalColors.current.darkGrey03,
            disabledCheckedTrackColor = LocalColors.current.whiteAlpha02,
            disabledUncheckedThumbColor = LocalColors.current.darkGrey03,
            disabledUncheckedTrackColor = LocalColors.current.whiteAlpha02
        )
    }
}