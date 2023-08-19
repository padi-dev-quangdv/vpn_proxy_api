package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.midterm.securevpnproxy.base.compose.LabelMedium
import com.midterm.securevpnproxy.base.compose.LocalColors

@Composable
fun ClassicHeader(
    title: String,
    startContent: @Composable BoxScope.() -> Unit,
    endContent: @Composable BoxScope.() -> Unit
) {
    BaseHeader(startContent = startContent, centerContent = {
        Text(
            text = title,
            style = LabelMedium,
            color = LocalColors.current.lightGrey03
        )
    }, endContent = endContent)
}


@Preview
@Composable
fun ClassicHeaderPreview() {
    ClassicHeader(title = "Bitcastle", startContent = {}, endContent = {})
}