package com.midterm.securevpnproxy.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.HomeActivity
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(layoutId = R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            showKeyBoard(etPassword)
        }
    }

    private fun setErrorEditText(error: Boolean) {
        if (error) {
            binding.tvError.visibility = View.VISIBLE
            binding.etPassword.setBackgroundResource(R.drawable.bg_edit_text_error)
        } else {
            binding.tvError.visibility = View.GONE
            binding.etPassword.setBackgroundResource(R.drawable.bg_edit_text)
        }
    }

    private fun reinitializeUI() {
        binding.apply {
            etEmail.text.clear()
            etPassword.text.clear()
            imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
            etEmail.requestFocus()
        }
    }

    override fun initData() {
        binding.etPassword.setText("agasdgaga")
    }

    override fun initViewListener() {
        binding.apply {
            etPassword.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoard(etPassword)
                    true
                } else {
                    false
                }
            }

            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                viewModel.login(email, password)
                reinitializeUI()
                val intent =
                    Intent(this@LoginFragment.requireContext(), HomeActivity::class.java)
                startActivity(intent)
            }
            tvForgotPassword.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                findNavController().navigate(action)
            }
            tvNavigateToRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }

            imageDisplayPassword.setOnClickListener {
                if (etPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                    imageDisplayPassword.setImageResource(R.drawable.ic_hide_password)
                    etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
                }
            }
        }
    }

    override fun initObserver() {

    }

    override fun initView() {
    }
}