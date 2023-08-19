package com.tanify.library.authentication.data.dto.firestore

data class FireStoreDeviceModel(
    val appVersion: String,
    val manufacturer: String,
    val name: String,
    val osVersion: String,
    val token: String,
    val type: OsType,
    val userId: String,
)

enum class OsType {
    Android,
    Windows,
    Mac
}