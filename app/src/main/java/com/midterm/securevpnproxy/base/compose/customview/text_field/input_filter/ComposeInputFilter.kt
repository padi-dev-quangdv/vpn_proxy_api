package com.midterm.securevpnproxy.base.compose.customview.text_field.input_filter

interface ComposeInputFilter {
    fun validate(before: String, after: String, next: (String) -> Unit = {})
    fun truncate(after: String): String
}