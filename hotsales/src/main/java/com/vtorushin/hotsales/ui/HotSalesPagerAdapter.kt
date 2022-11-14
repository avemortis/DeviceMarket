package com.vtorushin.hotsales.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vtorushin.hotsales.databinding.ItemHotSalesBinding
import com.vtorushin.hotsales.models.HotSalesItem

class HotSalesPagerAdapter(var items: List<HotSalesItem>) :
    RecyclerView.Adapter<HotSalesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HotSalesViewHolder(
        ItemHotSalesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HotSalesViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position].picture)
            .centerCrop()
            .into(holder.image)
        holder.title.text = items[position].title
        holder.subtitle.text = items[position].subtitle
        holder.isNewBudge.isVisible = items[position].isNew
    }

    override fun getItemCount() = items.size
}

class HotSalesViewHolder(binding: ItemHotSalesBinding) : RecyclerView.ViewHolder(binding.root) {
    val image = binding.itemHotSalesCover
    val title = binding.itemHotSalesTitle
    val subtitle = binding.itemHotSalesSubtitle
    val isNewBudge = binding.itemHotSalesNewBudge
}