package com.midterm.securevpnproxy.base.compose

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//TextSize
val HeadingXXLargeTextSize = 40.sp
val HeadingXLargeTextSize = 36.sp
val HeadingLargeTextSize = 32.sp
val HeadingMediumTextSize = 28.sp
val HeadingSmallTextSize = 24.sp
val HeadingXSmallTextSize = 20.sp

val LabelXLargeTextSize = 18.sp
val LabelLargeTextSize = 16.sp
val LabelMediumTextSize = 14.sp
val LabelSmallTextSize = 12.sp
val LabelXSmallTextSize = 10.sp

val ParagraphXLargeTextSize = 18.sp
val ParagraphLargeTextSize = 16.sp
val ParagraphMediumTextSize = 14.sp
val ParagraphSmallTextSize = 12.sp
val ParagraphXSmallTextSize = 10.sp

// TextStyle
val BaseTextStyle = TextStyle(
    fontFamily = appFontFamily,
    platformStyle = PlatformTextStyle(includeFontPadding = false)
)
val Heading = BaseTextStyle.copy(fontWeight = FontWeight.Medium)
val HeadingXXLarge = Heading.copy(fontSize = HeadingXXLargeTextSize, lineHeight = 52.sp)
val HeadingXLarge = Heading.copy(fontSize = HeadingXLargeTextSize, lineHeight = 44.sp)
val HeadingLarge = Heading.copy(fontSize = HeadingLargeTextSize, lineHeight = 40.sp)
val HeadingMedium = Heading.copy(fontSize = HeadingMediumTextSize, lineHeight = 36.sp)
val HeadingSmall = Heading.copy(fontSize = HeadingSmallTextSize, lineHeight = 32.sp)
val HeadingXSmall = Heading.copy(fontSize = HeadingXSmallTextSize, lineHeight = 28.sp)

val Label = BaseTextStyle.copy(fontWeight = FontWeight.SemiBold)
val LabelXLarge = Label.copy(fontSize = LabelXLargeTextSize, lineHeight = 24.sp)
val LabelLarge = Label.copy(fontSize = LabelLargeTextSize, lineHeight = 20.sp)
val LabelMedium = Label.copy(fontSize = LabelMediumTextSize, lineHeight = 16.sp)
val LabelSmall = Label.copy(
    fontSize = LabelSmallTextSize,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium
)
val LabelXSmall = Label.copy(
    fontSize = LabelXSmallTextSize,
    lineHeight = 12.sp,
    fontWeight = FontWeight.Medium
)

val Paragraph = BaseTextStyle.copy(fontWeight = FontWeight.Normal)
val ParagraphXLarge = Paragraph.copy(fontSize = ParagraphXLargeTextSize, lineHeight = 28.sp)
val ParagraphLarge = Paragraph.copy(fontSize = ParagraphLargeTextSize, lineHeight = 28.sp)
val ParagraphMedium = Paragraph.copy(fontSize = ParagraphMediumTextSize, lineHeight = 24.sp)
val ParagraphSmall = Paragraph.copy(fontSize = ParagraphSmallTextSize, lineHeight = 16.sp)
val ParagraphXSmall = Paragraph.copy(fontSize = ParagraphXSmallTextSize, lineHeight = 14.sp)