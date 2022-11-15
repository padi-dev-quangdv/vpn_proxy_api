package com.midterm.securevpnproxy.presentation.auth.register

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.presentation.MainActivity
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentRegisterBinding
import com.midterm.securevpnproxy.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterViewModel>(layoutId = R.layout.fragment_register) {

    override fun initData() {
        binding.apply {
            inputPassword.etInput.transformationMethod =
                PasswordTransformationMethod.getInstance()
            imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
            inputConfirmPassword.etInput.transformationMethod =
                PasswordTransformationMethod.getInstance()
            imageDisplayConfirmPassword.setImageResource(R.drawable.ic_display_password)
        }
    }

    private fun gotoHome() {
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

    private fun gotoLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun displayHidePassword(etInput: EditText,imageDisplayPassword: ImageView) {
        binding.apply {
            if (etInput.transformationMethod == PasswordTransformationMethod.getInstance()) {
                imageDisplayPassword.setImageResource(R.drawable.ic_hide_password)
                etInput.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                etInput.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
            }
        }
    }

    override fun initViewListener() {
        binding.btnRegister.setOnClickListener(this)
        binding.tvNavigateToLogin.setOnClickListener(this)
        binding.imageDisplayPassword.setOnClickListener(this)
        binding.imageDisplayConfirmPassword.setOnClickListener(this)
    }

    override fun onViewClicked(v: View) {
        super.onViewClicked(v)
        when (v.id) {
            binding.tvNavigateToLogin.id -> gotoLogin()
            binding.btnRegister.id -> gotoHome()
            binding.imageDisplayPassword.id -> displayHidePassword(binding.inputPassword.etInput,binding.imageDisplayPassword)
            binding.imageDisplayConfirmPassword.id -> displayHidePassword(binding.inputConfirmPassword.etInput, binding.imageDisplayConfirmPassword)
        }
    }

    override fun initObserver() {
        observe(viewModel.state) {
            binding.inputFullName.etInput.error = it.fullNameError
            binding.inputEmail.etInput.error = it.emailError
            binding.inputPassword.etInput.error = it.passwordError
            binding.inputConfirmPassword.etInput.error = it.confirmPasswordError
        }
        observe(viewModel.effect) {
            when (it) {
                is RegisterViewModel.ViewEffect.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                RegisterViewModel.ViewEffect.RegisterCompleted -> goToMain()
            }
        }
    }

    private fun goToMain() {
        val intent =
            Intent(this@RegisterFragment.requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    override fun initView() {
    }
}