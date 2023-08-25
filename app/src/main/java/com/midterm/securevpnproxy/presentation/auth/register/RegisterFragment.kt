package com.midterm.securevpnproxy.presentation.auth.register

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import androidx.core.view.isVisible
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.presentation.MainActivity
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseComposeFragment
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.compose.ButtonColors
import com.midterm.securevpnproxy.base.compose.HandleEffect
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.base.compose.MediumTextRegular
import com.midterm.securevpnproxy.base.compose.customview.LargeSolidButton
import com.midterm.securevpnproxy.base.compose.customview.text_field.AppEditText
import com.midterm.securevpnproxy.base.compose.customview.text_field.PasswordEditText
import com.midterm.securevpnproxy.databinding.FragmentRegisterBinding
import com.midterm.securevpnproxy.databinding.LayoutComposeOnlyBinding
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel
import com.midterm.securevpnproxy.presentation.auth.register.RegisterViewModel.*
import com.midterm.securevpnproxy.presentation.auth.ui.AuthHeaderUi
import com.midterm.securevpnproxy.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class RegisterFragment :
    BaseComposeFragment<LayoutComposeOnlyBinding, RegisterViewModel>(layoutId2 = R.layout.layout_compose_only), HandleEffect<RegisterViewModel.ViewEffect> {
    override fun getMainComposeView(): ComposeView = binding.composeView

    override val loadingView: View
        get() = binding.loading

    override val loadingState: Flow<Boolean>
        get() = viewModel.loadingState

    @Composable
    override fun MainComposeViewContent(modifier: Modifier) {
        val viewState by viewModel.state.collectAsStateWithLifecycle(initialValue = ViewState())
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            AuthHeaderUi(subtitle = stringResource(id = R.string.description_register))
            RegisterForm(
                fullName = viewState.fullName,
                email = viewState.email,
                password = viewState.password,
                confirmPassword = viewState.confirmPassword,
                fullNamePlaceholder = viewState.fullNamePlaceholder,
                emailPlaceholder = viewState.emailPlaceholder,
                passwordPlaceholder = viewState.passwordPlaceholder,
                fullNameError = viewState.fullNameError,
                emailError = viewState.emailError,
                passwordError = viewState.passwordError,
                confirmPasswordError = viewState.confirmPasswordError,
                showPassword = viewState.showPassword,
                onLoginClicked = ::gotoLogin,
                onRegisterClicked = { viewModel.onEvent(ViewEvent.Submit) },
                onTogglePasswordVisibility = { viewModel.onEvent(ViewEvent.TogglePasswordVisibility) },
                onFullNameChanged = { viewModel.onEvent(ViewEvent.UpdateFullName(it)) },
                onEmailChanged = { viewModel.onEvent(ViewEvent.UpdateEmail(it)) },
                onPasswordChanged = { viewModel.onEvent(ViewEvent.UpdatePassword(it)) },
                onConfirmPasswordChanged = {
                    viewModel.onEvent(
                        ViewEvent.UpdateConfirmPassword(
                            it
                        )
                    )
                }
            )
        }
    }

    @Composable
    private fun RegisterForm(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
        fullNamePlaceholder: String,
        emailPlaceholder: String,
        passwordPlaceholder: String,
        fullNameError: String?,
        emailError: String?,
        passwordError: String?,
        confirmPasswordError: String?,
        showPassword: Boolean,
        onLoginClicked: () -> Unit,
        onRegisterClicked: () -> Unit,
        onTogglePasswordVisibility: () -> Unit,
        modifier: Modifier = Modifier,
        onFullNameChanged: (String) -> Unit = {},
        onEmailChanged: (String) -> Unit = {},
        onPasswordChanged: (String) -> Unit = {},
        onConfirmPasswordChanged: (String) -> Unit = {},
    ) {
        Column(modifier) {
            AppEditText(
                label = stringResource(id = R.string.full_name),
                text = fullName,
                error = fullNameError,
                onValueChange = onFullNameChanged,
                placeHolder = fullNamePlaceholder
            )
            AppEditText(
                label = stringResource(id = R.string.email),
                text = email,
                error = emailError,
                onValueChange = onEmailChanged,
                placeHolder = emailPlaceholder,
                modifier = Modifier.padding(top = 16.dp),
            )
            PasswordEditText(
                label = stringResource(id = R.string.password),
                showPassword = showPassword,
                password = password,
                passwordError = passwordError,
                onPasswordChange = onPasswordChanged,
                passwordPlaceholder = passwordPlaceholder,
                onTogglePasswordVisibility = onTogglePasswordVisibility,
                modifier = Modifier.padding(top = 16.dp),
            )
            PasswordEditText(
                label = stringResource(id = R.string.confirm_password),
                showPassword = showPassword,
                password = confirmPassword,
                passwordError = confirmPasswordError,
                onPasswordChange = onConfirmPasswordChanged,
                passwordPlaceholder = passwordPlaceholder,
                onTogglePasswordVisibility = onTogglePasswordVisibility,
                modifier = Modifier.padding(top = 16.dp),
            )
            LargeSolidButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = stringResource(id = R.string.register),
                color = ButtonColors.buttonColorBlue(),
                onClick = onRegisterClicked
            )
            Text(
                modifier = modifier
                    .clickable(onClick = onLoginClicked)
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.register_navigate_to_login),
                style = MediumTextRegular,
                color = LocalColors.current.neutral70
            )
        }
    }

    override fun initData() {

    }

    private fun gotoLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    override fun initViewListener() {
    }

    override fun initObserver() {
    }

    private fun goToMain() {
        val intent =
            Intent(this@RegisterFragment.requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    override fun initView() {
    }

    override val provideEffectFlow: Flow<ViewEffect>
        get() = viewModel.effect

    override fun onEffectTriggered(effect: ViewEffect?) {
        when (effect) {
            is ViewEffect.Error -> {
                Toast.makeText(
                    requireContext(),
                    (effect as ViewEffect.Error).message,
                    Toast.LENGTH_LONG
                ).show()
            }

            ViewEffect.RegisterCompleted -> goToMain()
            else -> {

            }
        }
    }
}