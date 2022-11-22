package com.vtorushin.categories.usecases

import androidx.recyclerview.widget.RecyclerView
import com.vtorushin.categories.ui.recyclerview.CategoriesViewHolder

class SwitchActiveCategoryUseCase {
    fun invoke(recyclerView: RecyclerView, oldPosition: Int, newPosition: Int) {
        if (oldPosition != newPosition) {
            val oldPositionHolder = recyclerView.findViewHolderForAdapterPosition(oldPosition) as CategoriesViewHolder
            val newPositionHolder = recyclerView.findViewHolderForAdapterPosition(newPosition) as CategoriesViewHolder
            oldPositionHolder.setUncheck()
            newPositionHolder.setCheck()
        }
    }
}