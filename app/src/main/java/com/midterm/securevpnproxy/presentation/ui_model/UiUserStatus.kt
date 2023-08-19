package com.midterm.securevpnproxy.presentation.ui_model

import androidx.annotation.StringRes
import com.midterm.securevpnproxy.R
import com.tanify.library.authentication.domain.model.user_info.UserStatus

enum class UiUserStatus(@StringRes val displayText: Int) {
    Premium(displayText = R.string.premium),
    FreeTrial(displayText = R.string.free_trial);

    companion object {
        fun fromDomainModel(domainModel: UserStatus?): UiUserStatus {
            return when (domainModel) {
                UserStatus.Premium -> Premium
                UserStatus.FreeTrial -> FreeTrial
                else -> FreeTrial
            }
        }
    }
}