package com.tanify.library.dns.domain.model.server_list

import androidx.annotation.StringRes
import com.tanify.library.dns.R

enum class ServerGroupType(val id: String, val displayName: String, @StringRes val desc: Int) {
    Standard("standard", "Standard", R.string.mode_standard_desc),
    Intense("intense", "Intense", R.string.mode_intense_desc),
    Ultimate("ultimate", "Ultimate", R.string.mode_ultimate_desc)
}