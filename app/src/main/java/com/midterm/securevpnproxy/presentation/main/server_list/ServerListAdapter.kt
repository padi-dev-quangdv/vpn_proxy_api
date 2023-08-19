package com.midterm.securevpnproxy.presentation.main.server_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.midterm.securevpnproxy.databinding.ItemSeverListBinding
import com.midterm.securevpnproxy.presentation.dialog.SimpleMessageDialog
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.dns.domain.model.server_list.ServerModel

typealias GroupTypeItemClickListener = (ServerGroupType) -> Unit
typealias GroupTypeInfoClickListener = (ServerGroupType) -> Unit

class ServerListAdapter(
    private val itemClickListener: GroupTypeItemClickListener,
    private val infoClickListener: GroupTypeInfoClickListener,
) : RecyclerView.Adapter<ServerListAdapter.ItemViewHolder>() {

    private var selectedType: ServerGroupType? = null
    private var dataSource: MutableList<ServerGroupType> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedId(type: ServerGroupType?) {
        selectedType = type
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<ServerGroupType>) {
        this.dataSource.clear()
        this.dataSource.addAll(data)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemSeverListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ServerGroupType) {
            binding.apply {
                radioButton.isChecked = selectedType == item
                tvItemTitle.text = item.displayName

                radioButton.setOnCheckedChangeListener { _, selected ->
                    if (selected) {
                        selectItem(item)
                    }
                }
                tvItemTitle.setOnClickListener {
                    selectItem(item)
                }
                imageInformation.setOnClickListener {
                    infoClick(item)
                }
            }
        }

        private fun infoClick(item: ServerGroupType) {
            infoClickListener.invoke(item)
        }

        private fun selectItem(item: ServerGroupType) {
            itemClickListener.invoke(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServerListAdapter.ItemViewHolder {
        return ItemViewHolder(
            ItemSeverListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: ServerListAdapter.ItemViewHolder, position: Int) {
        val currentItem = dataSource[position]
        holder.bind(currentItem)
    }
}