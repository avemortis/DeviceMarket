package com.vtorushin.categories.ui.recyclerview

import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.vtorushin.categories.databinding.ItemCategoryBinding

class CategoriesAdapter(private val menu: Menu, val onBind:(holder: CategoriesViewHolder, position: Int) -> Unit) : RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriesViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.title.text = menu[position].title
        holder.icon.buttonDrawable = menu[position].icon
        onBind(holder, position)
    }

    override fun getItemCount() = menu.size()
}

class CategoriesViewHolder(binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    val icon = binding.icon
    val background = binding.background
    val title = binding.title

    fun switch() {
        background.isChecked = !background.isChecked
        icon.isChecked = !icon.isChecked
        title.isChecked = !title.isChecked
    }

    fun setUncheck() {
        background.isChecked = false
        icon.isChecked = false
        title.isChecked = false
    }

    fun setCheck() {
        background.isChecked = true
        icon.isChecked = true
        title.isChecked = true
    }
}