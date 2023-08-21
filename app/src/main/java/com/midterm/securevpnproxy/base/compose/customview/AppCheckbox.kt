package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.AbsWhite
import com.midterm.securevpnproxy.base.compose.LocalColors

/**
 * HieuTQ
 * Checkbox default padding is 12.dp.
 * In case you want to padding-left: 16.dp, you should padding the Row using modifier at 4.dp.
 * Don't try removing Checkbox padding due to its own accessibility.
 */

object AppCheckbox {
    @Composable
    fun View(
        modifier: Modifier = Modifier,
        checked: Boolean = false,
        enabled: Boolean = true,
        onCheckedChange: ((Boolean) -> Unit)?,
        content: @Composable RowScope.() -> Unit,
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                colors = color(),
                modifier = Modifier.padding(8.dp)
            )
            content()
        }
    }

    @Composable
    private fun color(): CheckboxColors {
        return CheckboxDefaults.colors(
            checkedColor = LocalColors.current.infoMain,
            uncheckedColor = LocalColors.current.neutral60,
            checkmarkColor = AbsWhite,
            disabledColor = LocalColors.current.neutral60,
        )
    }
}



