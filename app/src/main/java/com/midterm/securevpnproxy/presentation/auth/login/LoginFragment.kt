package com.midterm.securevpnproxy.presentation.auth.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseComposeFragment
import com.midterm.securevpnproxy.base.compose.ButtonColors
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.MediumTextRegular
import com.midterm.securevpnproxy.base.compose.MediumTextSemiBold
import com.midterm.securevpnproxy.base.compose.customview.LargeSolidButton
import com.midterm.securevpnproxy.base.compose.customview.text_field.AppEditText
import com.midterm.securevpnproxy.databinding.LayoutComposeOnlyBinding
import com.midterm.securevpnproxy.presentation.MainActivity
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.auth.ui.AuthHeaderUi
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@AndroidEntryPoint
class LoginFragment :
    BaseComposeFragment<LayoutComposeOnlyBinding, LoginViewModel>(layoutId2 = R.layout.layout_compose_only) {
    override fun getMainComposeView(): ComposeView = binding.composeView

    @Composable
    override fun MainComposeViewContent() {
        Surface(modifier = Modifier.background(color = LocalColors.current.white)) {
            val viewState by viewModel.state.collectAsStateWithLifecycle(initialValue = LoginViewModel.ViewState())
            ListenEffect()
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
    }

    @Composable
    private fun ListenEffect() {
        val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
        LaunchedEffect(key1 = effect) {
            when (effect) {
                is LoginViewModel.ViewEffect.Error -> {
                    Toast.makeText(
                        requireContext(),
                        (effect as LoginViewModel.ViewEffect.Error).message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                LoginViewModel.ViewEffect.LoginSuccess -> handleLogin(true)
                else -> {

                }
            }
        }
    }

    @Composable
    private fun LoginForm(
        modifier: Modifier = Modifier,
        email: String,
        password: String,
        emailError: String?,
        passwordError: String?,
        showPassword: Boolean = false,
        emailPlaceholder: String,
        passwordPlaceholder: String,
        onEmailChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit,
        onLoginClicked: () -> Unit,
        onRegisterClicked: () -> Unit,
        onForgotPasswordClicked: () -> Unit,
        onTogglePasswordVisibility: () -> Unit,
    ) {
        Column(modifier) {
            AppEditText(
                label = stringResource(id = R.string.email),
                text = email,
                error = emailError,
                onValueChange = onEmailChange,
                placeHolder = emailPlaceholder
            )
            PasswordField(
                modifier = Modifier.padding(top = 24.dp),
                showPassword = showPassword,
                password = password,
                passwordError = passwordError,
                onPasswordChange = onPasswordChange,
                passwordPlaceholder = passwordPlaceholder,
                onTogglePasswordVisibility = onTogglePasswordVisibility
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

    @Composable
    private fun PasswordField(
        modifier: Modifier = Modifier,
        showPassword: Boolean,
        password: String,
        passwordError: String?,
        onPasswordChange: (String) -> Unit,
        onTogglePasswordVisibility: () -> Unit,
        passwordPlaceholder: String
    ) {
        val rightImage = if (showPassword) {
            R.drawable.ic_display_password
        } else {
            R.drawable.ic_hide_password
        }
        val passwordVisualTransformation = remember {
            PasswordVisualTransformation()
        }
        val visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            passwordVisualTransformation
        }
        AppEditText(
            modifier = modifier,
            label = stringResource(id = R.string.password),
            text = password,
            error = passwordError,
            onValueChange = onPasswordChange,
            placeHolder = passwordPlaceholder,
            rightImage = {
                Image(
                    modifier = Modifier.clickable(onClick = onTogglePasswordVisibility),
                    painter = painterResource(id = rightImage),
                    contentDescription = "Show/Hide password"
                )
            },
            visualTransformation = visualTransformation,
        )
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

}