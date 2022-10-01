package com.midterm.securevpnproxy.presentation.main.white_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midterm.securevpnproxy.R

class WhiteListViewModel : ViewModel() {

    val whiteAppList = MutableLiveData<List<WhiteListAppData>>()

    init {
        whiteAppList.value = listOf(
            WhiteListAppData(imageTitle = R.drawable.ic_facebook, content = "Facebook" ),
            WhiteListAppData(imageTitle = R.drawable.ic_facebook, content = "Twitter")
        )
    }
}