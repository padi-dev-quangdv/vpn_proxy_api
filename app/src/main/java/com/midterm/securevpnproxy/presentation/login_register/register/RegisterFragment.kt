package com.midterm.securevpnproxy.presentation.login_register.register

import android.content.Intent
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.MainActivity
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterViewModel>(layoutId = R.layout.fragment_register) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.btnRegister.setOnClickListener {
            val fullName = binding.inputFullName.etInput.text.toString()
            val email = binding.inputEmail.etInput.text.toString()
            val password = binding.inputPassword.etInput.text.toString()
            val confirmPassword = binding.inputConfirmPassword.etInput.text.toString()
            val event = RegisterViewModel.ViewEvent.RegisterEvent(
                fullName,
                email,
                password,
                confirmPassword
            )
            viewModel.onEvent(event)

        }
        binding.tvNavigateToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.inputFullName.etInput.error = it.fullNameError
            binding.inputEmail.etInput.error = it.emailError
            binding.inputPassword.etInput.error = it.passwordError
            binding.inputConfirmPassword.etInput.error = it.confirmPasswordError
        }

        viewModel.isEmailAlreadyExist.observe(viewLifecycleOwner) { isExist ->
            if (isExist) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_email_already_exist),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent =
                    Intent(this@RegisterFragment.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun initView() {
    }
}