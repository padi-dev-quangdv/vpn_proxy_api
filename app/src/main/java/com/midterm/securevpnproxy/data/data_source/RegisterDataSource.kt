package com.midterm.securevpnproxy.data.data_source

import android.app.Application
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class RegisterDataSource() {
    fun register(email: String, password: String) {
        Log.d("ViewModel", "inside datasource")
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }
}