package com.midterm.securevpnproxy.presentation.main.server_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServerListViewModel : ViewModel() {

    val allSeverDataList = MutableLiveData<List<ItemSeverListData>>()

    init {
        allSeverDataList.value = listOf(
            ItemSeverListData(isChecked = false, name = "Security filter"),
            ItemSeverListData(isChecked = false, name = "Adult filter"),
            ItemSeverListData(isChecked = false, name = "Family filter")
        )
    }

}