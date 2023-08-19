package com.tanify.library.authentication.domain.model.user_info

data class UserDataModel(
    val email: String? = "",
    val fullName: String? = "",
    val status: UserStatus,
)

enum class UserStatus {
    Premium,
    FreeTrial
}