package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LocalColors

@Composable
private fun BaseDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color = LocalColors.current.whiteAlpha01,
        thickness = 1.dp
    )
}

@Composable
fun ColumnScope.AppDivider(modifier: Modifier = Modifier) {
    BaseDivider(modifier.fillMaxWidth())
}

@Composable
fun RowScope.AppDivider(modifier: Modifier = Modifier) {
    BaseDivider(modifier.fillMaxHeight())
}