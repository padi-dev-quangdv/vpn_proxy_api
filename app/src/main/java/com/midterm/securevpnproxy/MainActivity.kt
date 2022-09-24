package com.midterm.securevpnproxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.midterm.securevpnproxy.presentation.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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