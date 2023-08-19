package com.midterm.securevpnproxy.base.compose.customview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WrappedPullRefresh(
    modifier: Modifier = Modifier,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh
    )

    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {
        content()
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            contentColor = Color.Black,
            backgroundColor = Color.White,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun ComposePullRefreshPreview() {

}