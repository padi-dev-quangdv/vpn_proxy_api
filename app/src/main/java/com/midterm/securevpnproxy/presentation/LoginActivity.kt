package com.midterm.securevpnproxy.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.midterm.securevpnproxy.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

//    override fun onBackPressed() {
//        val fragments = supportFragmentManager.fragments
//        for (fragment: Fragment in fragments) {
//            if (fragment is LoginFragment) {
//                super.onBackPressed()
//                finish()
//            } else {
//                supportFragmentManager.popBackStack()
//            }
//        }
//    }
}