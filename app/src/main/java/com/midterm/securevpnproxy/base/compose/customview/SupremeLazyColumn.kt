package com.midterm.securevpnproxy.base.compose.customview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.ParagraphMedium

@Composable
fun SupremeLazyColumn(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    showEmptyView: Boolean?,
    showType: EmptyShowType = EmptyShowType.OVERLAY,
    emptyView: @Composable (Modifier) -> Unit,
    content: LazyListScope.() -> Unit
) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = verticalArrangement,
            contentPadding = contentPadding,
        ) {
            content()
            if (showType == EmptyShowType.ITEM) {
                if (showEmptyView == true) {
                    item {
                        emptyView(Modifier.fillMaxWidth())
                    }
                }
            }
        }
        if (showType == EmptyShowType.OVERLAY) {
            if (showEmptyView == true) {
                emptyView(Modifier.align(Alignment.Center))
            }
        }
    }
}

/**
 * ITEM: Show empty view as the last item of the list. It fits for the case you have many view types.
 * OVERLAY: Show empty view in the center of the list. Fit for single view type.
 */
enum class EmptyShowType {
    ITEM,
    OVERLAY
}

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null,
    imageSize: DpSize = DpSize(128.dp, 128.dp),
    text: String? = null,
    textColor: Color = LocalColors.current.lightGrey07,
    content: @Composable ColumnScope.() -> Unit,
) {

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        image?.let {
            Image(
                painter = painterResource(id = image),
                contentDescription = text ?: "",
                modifier = Modifier.size(imageSize)
            )
        }
        if (image != null && text != null) {
            Box(modifier = Modifier.height(8.dp))
        }
        text?.let {
            Text(
                text = text,
                style = ParagraphMedium,
                color = textColor
            )
        }
        content()
    }
}