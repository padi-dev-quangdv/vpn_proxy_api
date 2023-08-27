package com.midterm.securevpnproxy.presentation.main.home.home_main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.compose.LargeTextMedium
import com.midterm.securevpnproxy.base.compose.LocalColors
import com.midterm.securevpnproxy.presentation.main.home.home_main.HomeMainViewModel.*
import com.midterm.securevpnproxy.presentation.main.home.home_main.ui.CurrentModeUi
import com.midterm.securevpnproxy.presentation.main.home.home_main.ui.VpnProtectUi
import com.midterm.securevpnproxy.presentation.main.home.home_main.ui.VpnToggleState
import com.midterm.securevpnproxy.presentation.main.home.home_main.ui.VpnToggleUi

@Composable
fun HomeMainScreen(
    onChangeProfileClicked: () -> Unit,
    onSwitchVpnState: (status: VpnToggleState) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeMainViewModel = viewModel(),
) {
    val viewState by
    viewModel.state.collectAsStateWithLifecycle(initialValue = ViewState())
    // handle effect
    val viewEffect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
    LaunchedEffect(viewEffect) {
        viewEffect ?: return@LaunchedEffect
        when (val effect = viewEffect!!) {
            is ViewEffect.TurnVpn -> {
                val newState = effect.newState
                onSwitchVpnState.invoke(newState)
            }
        }
    }

    Column(modifier = modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CurrentModeUi(
            title = viewState.currentGroupType.displayName,
            subTitle = stringResource(id = R.string.activated),
            rightActionClicked = onChangeProfileClicked,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        VpnToggleUi(colorSet = viewState.status, modifier = Modifier.clickable {
            viewModel.onEvent(ViewEvent.OnOffToggle)
        })
        Text(
            text = viewState.timer,
            style = LargeTextMedium,
            color = LocalColors.current.neutral90,
            modifier = Modifier.padding(top = 26.dp)
        )
        VpnProtectUi(
            type = viewState.status,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

