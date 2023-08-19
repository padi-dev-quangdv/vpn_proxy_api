package com.midterm.securevpnproxy.presentation.main.custom_block_list

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomBlockListViewModel @Inject constructor() :
    BaseViewModel<CustomBlockListViewModel.ViewState, CustomBlockListViewModel.ViewEvent, CustomBlockListViewModel.ViewEffect>(
        ViewState()
    ) {

    class ViewState : BaseViewState {

    }

    interface ViewEvent : BaseViewEvent {

    }

    interface ViewEffect : BaseViewEffect {

    }

    override fun onEvent(event: ViewEvent) {
        TODO("Not yet implemented")
    }
}