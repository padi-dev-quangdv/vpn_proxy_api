package com.midterm.securevpnproxy.base.compose.customview.text_field

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.midterm.securevpnproxy.R

@Composable
fun PasswordEditText(
    label: String,
    showPassword: Boolean,
    password: String,
    passwordError: String?,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    passwordPlaceholder: String,
    modifier: Modifier = Modifier,
    showPasswordRes: Int = R.drawable.ic_display_password,
    hidePasswordRes: Int = R.drawable.ic_hide_password,
) {
    val rightImage = if (showPassword) {
        showPasswordRes
    } else {
        hidePasswordRes
    }
    val passwordVisualTransformation = remember {
        PasswordVisualTransformation()
    }
    val visualTransformation = if (showPassword) {
        VisualTransformation.None
    } else {
        passwordVisualTransformation
    }
    AppEditText(
        modifier = modifier,
        label = label,
        text = password,
        error = passwordError,
        onValueChange = onPasswordChange,
        placeHolder = passwordPlaceholder,
        rightImage = {
            Image(
                modifier = Modifier.clickable(onClick = onTogglePasswordVisibility),
                painter = painterResource(id = rightImage),
                contentDescription = "Show/Hide password"
            )
        },
        visualTransformation = visualTransformation,
    )
}