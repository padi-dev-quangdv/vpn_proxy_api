package com.midterm.securevpnproxy.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.HomeActivity
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentLoginBinding
import com.midterm.securevpnproxy.presentation.base.ViewEvent
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

    private fun reinitializeUI() {
        binding.apply {
            inputEmail.etInput.text.clear()
            inputPassword.etInput.text.clear()
            imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
            inputEmail.etInput.requestFocus()
        }
    }

    override fun initData() {
    }

    override fun initViewListener() {
        binding.apply {
            inputPassword.etInput.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoard(inputPassword.etInput)
                    true
                } else {
                    false
                }
            }

            btnLogin.setOnClickListener {
                val email = inputEmail.etInput.text.toString()
                val password = inputPassword.etInput.text.toString()
                viewModel.onEvent(ViewEvent.LoginEvent(email,password))
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
                if (inputPassword.etInput.transformationMethod == PasswordTransformationMethod.getInstance()) {
                    imageDisplayPassword.setImageResource(R.drawable.ic_hide_password)
                    inputPassword.etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    inputPassword.etInput.transformationMethod = PasswordTransformationMethod.getInstance()
                    imageDisplayPassword.setImageResource(R.drawable.ic_display_password)
                }
            }
        }
    }

    override fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.inputEmail.etInput.error = it.emailError
            binding.inputPassword.etInput.error = it.passwordError
        }
        viewModel.isExistUser.observe(viewLifecycleOwner) { isExist ->
            if(isExist) {
                val intent =
                    Intent(requireActivity(), HomeActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(requireContext(),"user is not exist",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun initView() {
    }
}