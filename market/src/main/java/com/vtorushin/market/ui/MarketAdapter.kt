package com.vtorushin.market.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vtorushin.market.data.MarketItem
import com.vtorushin.market.databinding.HolderMarketItemBinding

class MarketAdapter(var items: List<MarketItem>) : RecyclerView.Adapter<MarketHolderView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MarketHolderView(
        HolderMarketItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: MarketHolderView, position: Int) {
        Glide.with(holder.itemView.context)
            .load(items[position].picture)
            .centerCrop()
            .into(holder.picture)
        holder.title.text = items[position].title
        holder.actualPrice.text = items[position].discountPrice.toString()
        holder.irrelevantPrice.text = items[position].priceWithoutDiscount.toString()
    }

    override fun getItemCount() = items.size
}

class MarketHolderView(binding: HolderMarketItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val picture = binding.holderMarketItemImage
    val title = binding.holderMarketItemTitle
    val actualPrice = binding.holderMarketItemActualPrice
    val irrelevantPrice = binding.holderMarketItemIrrelevantPrice
}