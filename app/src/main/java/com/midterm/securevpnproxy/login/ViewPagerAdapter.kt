package com.midterm.securevpnproxy.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.securevpnproxy.OnBoardingData
import com.midterm.securevpnproxy.R

class ViewPagerAdapter(
    val onBoardingDataList: List<OnBoardingData>,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(onBoardingData: OnBoardingData) {
            val currentImage = itemView.findViewById<ImageView>(R.id.imageContent)
            val currentTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val currentDescription = itemView.findViewById<TextView>(R.id.tvDescription)

            currentImage.setImageResource(onBoardingData.image)
            currentTitle.text = onBoardingData.title
            currentDescription.text = onBoardingData.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentItem = onBoardingDataList.get(position)
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return onBoardingDataList.size
    }
}