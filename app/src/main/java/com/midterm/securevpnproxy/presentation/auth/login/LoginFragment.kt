package com.midterm.securevpnproxy.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentLoginBinding
import com.midterm.securevpnproxy.presentation.MainActivity
import com.midterm.securevpnproxy.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(layoutId = R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            showKeyBoard(inputPassword.etInput)
        }
    }



    override fun initData() {
        binding.apply {
            inputPassword.etInput.transformationMethod =
                PasswordTransformationMethod.getInstance()
            imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
        }
        context

        checkIsLogin()
    }

    private fun checkIsLogin() {
        if(viewModel.isLogin()) {
            val intent =
                Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        binding.apply {
            val email = inputEmail.etInput.text.toString()
            val password = inputPassword.etInput.text.toString()
            viewModel.onEvent(LoginViewModel.ViewEvent.LoginEvent(email, password))
        }
    }

    private fun gotoForgotPassword() {
        val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }

    private fun gotoRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    private fun displayHidePassword() {
        binding.apply {
            if (inputPassword.etInput.transformationMethod == PasswordTransformationMethod.getInstance()) {
                imageDisplayPassword.setImageResource(R.drawable.ic_hide_password)
                inputPassword.etInput.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                inputPassword.etInput.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
            }
        }
    }

    override fun initViewListener() {
        binding.apply {
            inputPassword.etInput.setOnEditorActionListener { _, i, _ ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoard(inputPassword.etInput)
                    true
                } else {
                    false
                }
            }
            btnLogin.setOnClickListener(this@LoginFragment)
            tvForgotPassword.setOnClickListener(this@LoginFragment)
            tvNavigateToRegister.setOnClickListener(this@LoginFragment)
            imageDisplayPassword.setOnClickListener(this@LoginFragment)
        }
    }

    override fun onViewClicked(v: View) {
        super.onViewClicked(v)
        when (v.id) {
            binding.btnLogin.id -> login()
            binding.tvForgotPassword.id -> gotoForgotPassword()
            binding.tvNavigateToRegister.id -> gotoRegister()
            binding.imageDisplayPassword.id -> displayHidePassword()
        }
    }

    override fun initObserver() {
        observe(viewModel.state) {
            binding.inputEmail.etInput.error = it.emailError
            binding.inputPassword.etInput.error = it.passwordError
        }
        viewModel.isUserExist.observe(viewLifecycleOwner) { isExist ->
            if (isExist) {
                viewModel.checkLogin()
                val intent =
                    Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_user_not_exist),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewModel.currentUser.observe(viewLifecycleOwner) { currentUser ->
            if (currentUser != null) {

            }
        }
    }

    override fun initView() {
    }

}