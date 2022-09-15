package com.midterm.securevpnproxy.data.data_source

import com.google.firebase.auth.FirebaseAuth

class LoginDataSource {
    fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
    }
}