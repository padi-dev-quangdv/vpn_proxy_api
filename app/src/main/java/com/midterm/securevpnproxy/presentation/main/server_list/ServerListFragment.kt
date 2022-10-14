package com.midterm.securevpnproxy.presentation.main.server_list

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentSeverListBinding

class ServerListFragment :
    BaseFragment<FragmentSeverListBinding, ServerListViewModel>(layoutId = R.layout.fragment_sever_list) {

    private val navigationArgs: ServerListFragmentArgs by navArgs()
    private lateinit var filter: String
    private lateinit var adapter: ServerListAdapter
    private lateinit var itemClickListener: ItemClickListener

    override fun initData() {
        filter = navigationArgs.filter

        itemClickListener = object : ItemClickListener {
            override fun onClick() {
                binding.rcvFilter.post(Runnable {
                    adapter.notifyDataSetChanged()
                })
            }
        }
        adapter = ServerListAdapter( itemClickListener,0)
        binding.rcvFilter.adapter = adapter
        viewModel.allSeverDataList.observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.submitList(list)
            }
       }
    }

    override fun initViewListener() {
        binding.layoutHeader.iconRight.setOnClickListener {
            val action = ServerListFragmentDirections.actionSeverListFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }

    override fun initObserver() {
    }

    override fun initView() {

    }
}