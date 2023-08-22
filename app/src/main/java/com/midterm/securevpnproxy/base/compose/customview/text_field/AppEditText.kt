package com.midterm.securevpnproxy.base.compose.customview.text_field

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.base.compose.LargeTextHeight
import com.midterm.securevpnproxy.base.compose.LargeTextMedium
import com.midterm.securevpnproxy.base.compose.LargeTextSize
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.MediumTextSemiBold
import com.midterm.securevpnproxy.base.compose.SmallTextRegular

@Preview
@Composable
private fun AppEditTextPreview() {
    AppEditText(
        label = "Email",
        error = "Error message",
        text = "",
        placeHolder = "Enter email"
    ) {}
}

@Composable
fun AppEditText(
    modifier: Modifier = Modifier,
    label: String?,
    error: String?,
    placeHolder: String = "",
    text: String = "",
    borderColor: Color = if (error != null) {
        LocalColors.current.dangerMain
    } else {
        LocalColors.current.infoMain
    },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    rightImage: @Composable (ColumnScope.() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        label?.let {
            Text(text = it, style = MediumTextSemiBold, color = LocalColors.current.neutral80)
        }
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.weight(1f)) {
                val showPlaceHolder = text.isEmpty()
                if (showPlaceHolder) {
                    Text(
                        text = placeHolder,
                        style = LargeTextMedium,
                        color = LocalColors.current.neutral70
                    )
                }
                BaseTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        color = LocalColors.current.neutral70,
                        fontSize = LargeTextSize,
                        lineHeight = LargeTextHeight,
                        fontWeight = FontWeight.Medium,
                    ),
                    visualTransformation = visualTransformation,
                    singleLine = true,
                )
            }
            rightImage?.invoke(this@Column)
        }
        AnimatedVisibility(visible = error != null) {
            error?.let {
                Text(text = it, style = SmallTextRegular, color = LocalColors.current.dangerMain)
            }
        }

    }
}