package com.midterm.securevpnproxy.presentation.main.sever_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midterm.securevpnproxy.R

class SeverListViewModel : ViewModel() {

    val allSeverDataList = MutableLiveData<List<ItemSeverListData>>()

    init {
        allSeverDataList.value = listOf(
            ItemSeverListData(isChecked = false, name = "Security filter"),
            ItemSeverListData(isChecked = false, name = "Adult filter"),
            ItemSeverListData(isChecked = false, name = "Family filter")
        )
    }

}