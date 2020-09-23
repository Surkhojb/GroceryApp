package com.surkhojb.groceryapp.feature.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.surkhojb.groceryapp.model.GroceryItem

class GroceryDiffUtil(oldItems: List<GroceryItem>,
                      newItems: List<GroceryItem>) : DiffUtil.Callback() {
    private val newList: List<GroceryItem> = newItems
    private val oldList: List<GroceryItem> = oldItems

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos].id == newList[newPos].id
    }

    override fun getOldListSize(): Int {
        return oldList.count()
    }

    override fun getNewListSize(): Int {
        return newList.count()
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
       return oldList[oldPos].equals(newList[newPos])
    }

}