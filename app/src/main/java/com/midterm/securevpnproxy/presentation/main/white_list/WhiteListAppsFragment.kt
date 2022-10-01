package com.midterm.securevpnproxy.presentation.main.white_list

import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentWhiteListAppsBinding

class WhiteListAppsFragment :
    BaseFragment<FragmentWhiteListAppsBinding, WhiteListViewModel>(layoutId = R.layout.fragment_white_list_apps) {

    private lateinit var adapter: WhiteListAdapter

    override fun initData() {
        adapter = WhiteListAdapter()
        binding.rcvWhiteListApp.adapter = adapter
        viewModel.whiteAppList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
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