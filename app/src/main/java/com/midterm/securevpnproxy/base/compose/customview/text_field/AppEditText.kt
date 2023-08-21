package com.midterm.securevpnproxy.base.compose.customview.text_field

import android.media.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.DangerMain
import com.midterm.securevpnproxy.base.compose.InfoMain
import com.midterm.securevpnproxy.base.compose.LargeTextMedium
import com.midterm.securevpnproxy.base.compose.MediumTextSemiBold
import com.midterm.securevpnproxy.base.compose.Neutral70
import com.midterm.securevpnproxy.base.compose.Neutral80
import com.midterm.securevpnproxy.base.compose.SmallTextRegular

@Preview
@Composable
private fun AppEditTextPreview() {
    AppEditText(label = "Email", error = "Error message", onValueChange = {})
}

@Composable
fun AppEditText(
    modifier: Modifier = Modifier,
    label: String?,
    error: String?,
    placeHolder: String = "",
    text: String = "",
    borderColor: Color = if (error != null) {
        DangerMain
    } else {
        InfoMain
    },
    rightImage: Image? = null,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        label?.let {
            Text(text = it, style = MediumTextSemiBold, color = Neutral80)
        }
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RectangleShape
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                val showPlaceHolder = text.isEmpty()
                if (showPlaceHolder) {
                    Text(text = placeHolder, style = LargeTextMedium, color = Neutral70)
                }
                BaseTextField(value = text, onValueChange = onValueChange)
            }
            rightImage
        }
        error?.let {
            Text(text = it, style = SmallTextRegular, color = DangerMain)
        }
    }
}