package com.midterm.securevpnproxy.presentation.main.server_list

import android.view.View
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentSeverListBinding
import com.midterm.securevpnproxy.presentation.dialog.SimpleMessageDialog
import com.midterm.securevpnproxy.presentation.main.server_list.ServerListViewModel.ViewEvent
import com.midterm.securevpnproxy.util.extensions.observe
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerListFragment :
    BaseFragment<FragmentSeverListBinding, ServerListViewModel>(layoutId = R.layout.fragment_sever_list) {

    private val adapter: ServerListAdapter by lazy {
        ServerListAdapter(itemClickListener = object : GroupTypeItemClickListener {
            override fun invoke(item: ServerGroupType) {
                viewModel.onEvent(ViewEvent.SelectServerGroupType(item))
            }
        }, infoClickListener = object : GroupTypeInfoClickListener {
            override fun invoke(item: ServerGroupType) {
                showGroupTypeInformation(item)
            }
        })
    }

    private fun showGroupTypeInformation(item: ServerGroupType) {
        val dialog =
            SimpleMessageDialog(title = item.displayName, description = getString(item.desc))
        dialog.show(parentFragmentManager, SimpleMessageDialog::class.java.canonicalName)
    }

    override fun initData() {
        binding.rcvFilter.adapter = adapter
    }

    override fun initViewListener() {
        binding.layoutHeader.iconRight.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)
    }

    override fun onViewClicked(view: View) {
        super.onViewClicked(view)
        when (view) {
            binding.layoutHeader.iconRight -> {
                val action = ServerListFragmentDirections.actionSeverListFragmentToProfileFragment()
                findNavController().navigate(action)
            }
            binding.btnBack -> {
                findNavController().popBackStack()
            }
        }
    }

    override fun initObserver() {
        observe(flow = viewModel.state, observer = ::handleStateChanges)
    }

    private fun handleStateChanges(state: ServerListViewModel.ViewState) {
        adapter.updateData(state.groupTypes)
        adapter.setSelectedId(state.selectedGroupType)
    }

    override fun initView() {

    }
}