package com.midterm.securevpnproxy.base.compose.customview

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.R

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    @DrawableRes drawableRes: Int,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(padding)
    ) {
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = contentDescription,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
fun ImageButtonPreview() {
    ImageButton(drawableRes = R.drawable.ic_back) {
        //Clicked
    }
}