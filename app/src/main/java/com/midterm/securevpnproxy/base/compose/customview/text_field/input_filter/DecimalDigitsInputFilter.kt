package com.midterm.securevpnproxy.base.compose.customview.text_field.input_filter

import timber.log.Timber
import java.util.regex.Pattern

class DecimalDigitsInputFilter(
    private val digitsBeforeZero: Int = 999,
    private val digitsAfterZero: Int
) : ComposeInputFilter {

    private val limitedIntegralPattern: Pattern =
        Pattern.compile("[0-9]{0,$digitsBeforeZero}+((\\.[0-9]{0,$digitsAfterZero})?)||(\\.)?")

    private val unlimitedIntegralPattern: Pattern =
        Pattern.compile("[0-9]{0,999}+((\\.[0-9]{0,$digitsAfterZero})?)||(\\.)?")

    private val noDecimalPattern: Pattern =
        Pattern.compile("[0-9]{0,$digitsBeforeZero}")

    override fun validate(before: String, after: String, next: (String) -> Unit) {
        val matcher = if (before.length > after.length) {
            unlimitedIntegralPattern.matcher(after)
        } else if (digitsAfterZero == 0) {
            noDecimalPattern.matcher(after)
        } else {
            limitedIntegralPattern.matcher(after)
        }
        if (matcher.matches()) {
            next(after)
            return
        } else {
            Timber.d("==>")
        }
    }

    override fun truncate(after: String): String {
        val isNumber = after.toBigDecimalOrNull() != null
        if (!isNumber) {
            return ""
        }
        val dotPosition = after.indexOf('.')
        if (dotPosition == -1) {
            // check if the number of digits before dot is greater than digitsBeforeZero
            val digitsBeforeDot = after.length
            if (digitsBeforeDot > digitsBeforeZero) {
                return ""
            }
        } else {
            // check if the number of digits before dot is greater than digitsBeforeZero
            val digitsBeforeDot = after.substring(0, dotPosition).length
            if (digitsBeforeDot > digitsBeforeZero) {
                return ""
            }
            // check if the number of digits after dot is greater than digitsAfterZero
            val digitsAfterDot = after.substring(dotPosition + 1).length
            if (digitsAfterDot > digitsAfterZero) {
                return ""
            }
        }
        return after
    }
}