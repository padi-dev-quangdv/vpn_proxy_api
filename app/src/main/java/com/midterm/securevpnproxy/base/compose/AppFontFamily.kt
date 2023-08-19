package com.midterm.securevpnproxy.base.compose

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.midterm.securevpnproxy.R

private val light = Font(
    resId = R.font.plus_jakarta_text_italic,
    weight = FontWeight.Light,
    style = FontStyle.Normal
)
private val regular = Font(
    resId = R.font.plus_jakarta_text_regular,
    weight = FontWeight.Normal,
    style = FontStyle.Normal
)
private val medium = Font(
    resId = R.font.plus_jakarta_text_medium,
    weight = FontWeight.Medium,
    style = FontStyle.Normal
)
private val semiBold = Font(
    resId = R.font.plus_jakarta_text_semi_bold,
    weight = FontWeight.SemiBold,
    style = FontStyle.Normal
)
private val bold = Font(
    resId = R.font.plus_jakarta_text_bold,
    weight = FontWeight.Bold,
    style = FontStyle.Normal
)

val appFontFamily = FontFamily(light, regular, medium, semiBold, bold)

val appTypography = Typography(defaultFontFamily = appFontFamily)
