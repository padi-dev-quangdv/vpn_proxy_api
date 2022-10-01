package com.midterm.securevpnproxy.presentation.main.sever_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.midterm.securevpnproxy.databinding.ItemSeverListBinding

class SeverListAdapter(
    private val itemClickListener: ItemClickListener,
    private var currentPosition: Int
) : ListAdapter<ItemSeverListData, SeverListAdapter.ItemViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ItemSeverListData>() {
            override fun areItemsTheSame(
                oldItem: ItemSeverListData,
                newItem: ItemSeverListData
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ItemSeverListData,
                newItem: ItemSeverListData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

     inner class ItemViewHolder(private val binding: ItemSeverListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemSeverListData: ItemSeverListData) {
            binding.apply {
                radioButton.isChecked = currentPosition == adapterPosition
                tvItemTitle.text = itemSeverListData.name

                radioButton.setOnCheckedChangeListener { _, b ->
                    if (b) {
                        currentPosition = adapterPosition
                        itemClickListener.onClick()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeverListAdapter.ItemViewHolder {
        return ItemViewHolder(
            ItemSeverListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: SeverListAdapter.ItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}