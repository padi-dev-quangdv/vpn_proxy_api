package com.midterm.securevpnproxy.presentation.login_register.splash


import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class OnBoardingViewModel : ViewModel() {

    private var sharedPreferences: SharedPreferences? = null

    fun savePrefData(fragment: Fragment) {
        sharedPreferences = fragment.activity?.applicationContext?.getSharedPreferences(
            "pref",
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    fun restorePrefData(fragment: Fragment): Boolean {
        sharedPreferences = fragment.activity?.applicationContext?.getSharedPreferences(
            "pref",
            Context.MODE_PRIVATE
        )
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }

}