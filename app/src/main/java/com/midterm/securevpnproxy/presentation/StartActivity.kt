package com.midterm.securevpnproxy.presentation

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseActivity
import com.midterm.securevpnproxy.databinding.ActivityStartBinding
import com.midterm.securevpnproxy.util.extensions.observe
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import com.tanify.library.authentication.domain.usecase.auth_state.AuthStateUseCase
import com.tanify.library.libcore.usecase.ResultModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding, StartViewModel>(R.layout.activity_start) {
    override fun initData() {
    }

    override fun initViewListener() {
    }

    override fun initObserver() {
        observe(viewModel.state) { handleState(it) }
    }

    private fun handleState(state: StartViewModel.ViewState) {}
    override fun initView() {
    }
}