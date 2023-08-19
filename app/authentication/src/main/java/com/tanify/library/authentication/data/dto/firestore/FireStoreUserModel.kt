package com.tanify.library.authentication.data.dto.firestore

import com.google.firebase.Timestamp
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.authentication.domain.model.user_info.UserStatus

data class FireStoreUserModel(
    val email: String? = null,
    val name: String? = null,
    val createdAt: Timestamp? = null,
    val status: String? = null,
) {
    fun transformStatusToModel(): UserStatus {
        return when (status) {
            "premium" -> UserStatus.Premium
            "trial" -> UserStatus.FreeTrial
            else -> UserStatus.FreeTrial
        }
    }
}

fun FireStoreUserModel.toModel(): UserDataModel {
    return UserDataModel(email = email, fullName = name, status = transformStatusToModel())
}