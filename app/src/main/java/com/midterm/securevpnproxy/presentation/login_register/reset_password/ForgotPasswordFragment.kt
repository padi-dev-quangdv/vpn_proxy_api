package com.midterm.securevpnproxy.presentation.login_register.reset_password

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(layoutId = R.layout.fragment_forgot_password) {

    override fun initData() {
        binding.etEmail.text?.clear()
    }

    override fun initViewListener() {
        binding.tvNavigateToLogin.setOnClickListener {
            val action =
                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        binding.btnSend.setOnClickListener {
            val email = binding.etEmail.text.toString()
            viewModel.onEvent(ForgotPasswordViewModel.ViewEvent.ResetPasswordEvent(email = email))
        }
    }

    override fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.etEmail.error = it.emailError
        }
        viewModel.isEmailExist.observe(viewLifecycleOwner) {
            if(it) {
                val action =
                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToCheckEmailFragment()
                findNavController().navigate(action)
                Toast.makeText(requireContext(),getString(R.string.check_mail),Toast.LENGTH_LONG).show()
                viewModel.doneNavigating()
            }
        }
    }

    override fun initView() {
        binding.etEmail.text?.clear()
    }


}