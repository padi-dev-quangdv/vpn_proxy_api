package com.midterm.securevpnproxy.presentation.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentRegisterBinding
import com.midterm.securevpnproxy.presentation.register.RegisterViewModel.ViewEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterViewModel>(layoutId = R.layout.fragment_register) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.btnRegister.setOnClickListener {
            val fullName = binding.inputFullName.etInput.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.onEvent(ViewEvent.RegisterEvent(fullName, email, password))
        }
        binding.tvNavigateToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.inputFullName.etInput.error = it.fullNameError
            binding.etEmail.error = it.emailError
            binding.etPassword.error = it.passwordError
        }
    }

    override fun initView() {
    }
}