package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LocalColors

@Composable
fun BaseHeader(
    modifier: Modifier = Modifier,
    dividerColor: Color = LocalColors.current.whiteAlpha01,
    startContent: @Composable BoxScope.() -> Unit,
    centerContent: @Composable BoxScope.() -> Unit,
    endContent: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.height(56.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            startContent()
        }
        Box(modifier = Modifier.align(Alignment.Center)) {
            centerContent()
        }
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            endContent()
        }
        Divider(color = dividerColor, modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Preview
@Composable
fun FullScreenHeaderPreview() {
    BaseHeader(startContent = {
        Text(text = "Start")
    }, centerContent = {
        Text("Center")
    },
        endContent = {
            Text(text = "End")
        },
        dividerColor = Color.Blue
    )
}