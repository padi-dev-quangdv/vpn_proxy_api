package com.midterm.securevpnproxy.presentation.ui_model

import com.tanify.library.authentication.domain.model.user_info.UserDataModel

data class UiUserDataModel(
    val fullName: String?,
    val email: String?,
    val status: UiUserStatus,
) {
    companion object {
        fun fromDomainModel(domainModel: UserDataModel): UiUserDataModel {
            return UiUserDataModel(
                fullName = domainModel.fullName,
                email = domainModel.email,
                status = UiUserStatus.fromDomainModel(domainModel.status)
            )
        }
    }
}
