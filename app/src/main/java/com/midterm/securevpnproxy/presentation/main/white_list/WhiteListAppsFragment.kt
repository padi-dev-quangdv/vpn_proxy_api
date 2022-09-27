package com.midterm.securevpnproxy.presentation.main.white_list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentWhiteListAppsBinding
import com.midterm.securevpnproxy.presentation.main.sever_list.SeverListAdapter

class WhiteListAppsFragment :
    BaseFragment<FragmentWhiteListAppsBinding, WhiteListViewModel>(layoutId = R.layout.fragment_white_list_apps) {

    private lateinit var adapter: WhiteListAdapter

    override fun initData() {
        val dataList = listOf(
            WhiteListAppData(imageTitle = R.drawable.ic_facebook, getString(R.string.facebook)),
            WhiteListAppData(imageTitle = R.drawable.ic_facebook, getString(R.string.twitter))
        )
        adapter = WhiteListAdapter(dataList)
        binding.rcvWhiteListApp.adapter = adapter
        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        binding.rcvWhiteListApp.layoutManager = manager
    }

    override fun initViewListener() {
        binding.apply {
            layoutHeader.iconLeft.setOnClickListener {
                val action =
                    WhiteListAppsFragmentDirections.actionWhiteListAppsFragmentToSettingsFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }

}