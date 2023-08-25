package com.midterm.securevpnproxy.presentation.auth.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseComposeFragment
import com.midterm.securevpnproxy.base.compose.ButtonColors
import com.midterm.securevpnproxy.base.compose.HandleEffect
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.MediumTextRegular
import com.midterm.securevpnproxy.base.compose.MediumTextSemiBold
import com.midterm.securevpnproxy.base.compose.customview.LargeSolidButton
import com.midterm.securevpnproxy.base.compose.customview.text_field.AppEditText
import com.midterm.securevpnproxy.base.compose.customview.text_field.PasswordEditText
import com.midterm.securevpnproxy.databinding.LayoutComposeOnlyBinding
import com.midterm.securevpnproxy.presentation.MainActivity
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.auth.ui.AuthHeaderUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class LoginFragment :
    BaseComposeFragment<LayoutComposeOnlyBinding, LoginViewModel>(layoutId2 = R.layout.layout_compose_only),
    HandleEffect<LoginViewModel.ViewEffect> {
    override fun getMainComposeView(): ComposeView = binding.composeView

    override val loadingView: View
        get() = binding.loading

    override val loadingState: Flow<Boolean>
        get() = viewModel.loadingState

    @Composable
    override fun MainComposeViewContent(modifier: Modifier) {
        val viewState by viewModel.state.collectAsStateWithLifecycle(initialValue = LoginViewModel.ViewState())
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            AuthHeaderUi()
            LoginForm(
                email = viewState.email,
                password = viewState.password,
                emailError = viewState.emailError,
                passwordError = viewState.passwordError,
                showPassword = viewState.showPassword,
                emailPlaceholder = viewState.emailPlaceholder,
                passwordPlaceholder = viewState.passwordPlaceholder,
                onEmailChange = { viewModel.onEvent(ViewEvent.UpdateEmail(it)) },
                onPasswordChange = { viewModel.onEvent(ViewEvent.UpdatePassword(it)) },
                onLoginClicked = { viewModel.onEvent(ViewEvent.Submit) },
                onRegisterClicked = ::gotoRegister,
                onForgotPasswordClicked = ::gotoForgotPassword,
                onTogglePasswordVisibility = { viewModel.onEvent(ViewEvent.TogglePasswordVisibility) }
            )
        }
    }

    @Composable
    private fun LoginForm(
        email: String,
        password: String,
        emailError: String?,
        passwordError: String?,
        emailPlaceholder: String,
        passwordPlaceholder: String,
        onEmailChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit,
        onLoginClicked: () -> Unit,
        onRegisterClicked: () -> Unit,
        onForgotPasswordClicked: () -> Unit,
        onTogglePasswordVisibility: () -> Unit,
        modifier: Modifier = Modifier,
        showPassword: Boolean = false,
    ) {
        Column(modifier) {
            AppEditText(
                label = stringResource(id = R.string.email),
                text = email,
                error = emailError,
                onValueChange = onEmailChange,
                placeHolder = emailPlaceholder
            )
            PasswordEditText(
                label = stringResource(id = R.string.password),
                showPassword = showPassword,
                password = password,
                passwordError = passwordError,
                onPasswordChange = onPasswordChange,
                passwordPlaceholder = passwordPlaceholder,
                onTogglePasswordVisibility = onTogglePasswordVisibility,
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onForgotPasswordClicked)
                    .align(Alignment.End),
                text = stringResource(id = R.string.forgot_password),
                style = MediumTextSemiBold,
                color = LocalColors.current.neutral90,
            )
            LargeSolidButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                text = stringResource(id = R.string.login),
                color = ButtonColors.buttonColorBlue(),
                onClick = onLoginClicked
            )
            Text(
                modifier = modifier
                    .clickable(onClick = onRegisterClicked)
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.navigate_to_register),
                style = MediumTextRegular,
                color = LocalColors.current.neutral70
            )
        }
    }

    override fun initData() {
        checkIsLogin()
    }

    private fun checkIsLogin() {
        viewModel.onEvent(ViewEvent.CheckLogin)
    }

    private fun gotoForgotPassword() {
        val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }

    private fun gotoRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    private fun handleLogin(loggedIn: Boolean) {
        if (loggedIn) {
            val intent =
                Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initView() {
    }

    override val provideEffectFlow: Flow<LoginViewModel.ViewEffect>
        get() = viewModel.effect

    override fun onEffectTriggered(effect: LoginViewModel.ViewEffect?) {
        when (effect) {
            is LoginViewModel.ViewEffect.Error -> {
                Toast.makeText(
                    requireContext(),
                    effect.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            LoginViewModel.ViewEffect.LoginSuccess -> handleLogin(true)
            else -> {

            }
        }
    }
}