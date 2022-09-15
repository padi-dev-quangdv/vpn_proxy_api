package com.midterm.securevpnproxy.domain.model

data class User(
    val fullName: String,
    val email: String,
    val password: String
)

class InvalidNoteException(message: String): Exception(message)