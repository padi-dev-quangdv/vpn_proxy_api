package com.midterm.securevpnproxy.presentation

import android.annotation.SuppressLint
import android.content.Intent
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseActivity
import com.midterm.securevpnproxy.databinding.ActivitySplashBinding
import com.midterm.securevpnproxy.util.extensions.observe
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(
        R.layout.activity_splash
    ) {

    override fun initView() {

    }

    override fun initObserver() {
        observe(viewModel.state) { handleState(it) }
    }

    private fun handleState(state: SplashViewModel.ViewState) {
        state.authState?.let {
            when (it) {
                AuthState.LOGGED_IN -> handleLoggedIn()
                AuthState.LOGGED_OUT -> handleNotLoggedIn()
            }
        }
    }

    private fun handleLoggedIn() {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }


    private fun handleNotLoggedIn() {
        startActivity(Intent(this, StartActivity::class.java))
        this.finish()
    }

    override fun initData() {

    }

    override fun initViewListener() {
    }
}
