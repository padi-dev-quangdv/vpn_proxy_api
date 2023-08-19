package com.midterm.securevpnproxy.base.compose

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.midterm.securevpnproxy.base.compose.LocalColors

object ButtonColors {
    @Composable
    fun buttonColorWhite(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = AbsWhite,
            contentColor = AbsDarkGrey07,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

    @Composable
    fun buttonColorBlue(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = LocalColors.current.blue07,
            contentColor = AbsWhite,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

    @Composable
    fun buttonColorRed(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = LocalColors.current.red07,
            contentColor = AbsWhite,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

    @Composable
    fun buttonColorGreen(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = LocalColors.current.green07,
            contentColor = AbsWhite,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

    @Composable
    fun buttonColorWhiteAlpha(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = LocalColors.current.whiteAlpha02,
            contentColor = LocalColors.current.lightGrey03,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

    @Composable
    fun textButtonColor(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = LocalColors.current.lightGrey03,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

    @Composable
    fun hyperTextButtonColor(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = LocalColors.current.blue05,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }
    @Composable
    fun buttonColorDarkGrey08(): ButtonColors {
        return ButtonDefaults.buttonColors(
            backgroundColor = LocalColors.current.darkGrey08,
            contentColor = LocalColors.current.lightGrey03,
            disabledBackgroundColor = LocalColors.current.whiteAlpha02,
            disabledContentColor = LocalColors.current.darkGrey03
        )
    }

}