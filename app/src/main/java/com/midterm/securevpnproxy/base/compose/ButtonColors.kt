package com.midterm.securevpnproxy.base.compose

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.midterm.securevpnproxy.base.compose.LocalColors

object ButtonColors {
    @Composable
    fun buttonColorBlue(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = LocalColors.current.infoMain,
            contentColor = LocalColors.current.neutral10,
            disabledBackgroundColor = LocalColors.current.infoMain,
            disabledContentColor = LocalColors.current.neutral10
        )
    }

    @Composable
    fun textButtonColor(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = LocalColors.current.neutral90,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = LocalColors.current.neutral90
        )
    }

    @Composable
    fun outlinedButtonColorBlue(): ButtonColors {
        return ButtonDefaults.outlinedButtonColors(
            backgroundColor = LocalColors.current.white,
            contentColor = LocalColors.current.infoMain,
            disabledContentColor = LocalColors.current.neutral70
        )
    }
}