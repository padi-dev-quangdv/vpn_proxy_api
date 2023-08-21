package com.midterm.securevpnproxy.base.compose

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SmallTextSize = 12.sp
val MediumTextSize = 14.sp
val LargeTextSize = 16.sp

val SmallTextHeight = 16.sp
val MediumTextHeight = 20.sp
val LargeTextHeight = 24.sp

// TextStyle
val BaseTextStyle = TextStyle(
    fontFamily = appFontFamily,
    platformStyle = PlatformTextStyle(includeFontPadding = false)
)

// Regular
val RegularStyle = BaseTextStyle.copy(fontWeight = FontWeight.Normal)
val SmallTextRegular = RegularStyle.copy(fontSize = SmallTextSize, lineHeight = SmallTextHeight)
val MediumTextRegular = RegularStyle.copy(fontSize = MediumTextSize, lineHeight = MediumTextHeight)
val LargeTextRegular = RegularStyle.copy(fontSize = LargeTextSize, lineHeight = LargeTextHeight)
// Medium
val MediumStyle = BaseTextStyle.copy(fontWeight = FontWeight.Medium)
val SmallTextMedium = RegularStyle.copy(fontSize = SmallTextSize, lineHeight = SmallTextHeight)
val MediumTextMedium = RegularStyle.copy(fontSize = MediumTextSize, lineHeight = MediumTextHeight)
val LargeTextMedium = RegularStyle.copy(fontSize = LargeTextSize, lineHeight = LargeTextHeight)
// Semi bold
val SemiBoldStyle = BaseTextStyle.copy(fontWeight = FontWeight.SemiBold)
val SmallTextSemiBold = RegularStyle.copy(fontSize = SmallTextSize, lineHeight = SmallTextHeight)
val MediumTextSemiBold = RegularStyle.copy(fontSize = MediumTextSize, lineHeight = MediumTextHeight)
val LargeTextSemiBold = RegularStyle.copy(fontSize = LargeTextSize, lineHeight = LargeTextHeight)
// Bold
val BoldStyle = BaseTextStyle.copy(fontWeight = FontWeight.Bold)
val SmallTextBold = RegularStyle.copy(fontSize = SmallTextSize, lineHeight = SmallTextHeight)
val MediumTextBold = RegularStyle.copy(fontSize = MediumTextSize, lineHeight = MediumTextHeight)
val LargeTextBold = RegularStyle.copy(fontSize = LargeTextSize, lineHeight = LargeTextHeight)
