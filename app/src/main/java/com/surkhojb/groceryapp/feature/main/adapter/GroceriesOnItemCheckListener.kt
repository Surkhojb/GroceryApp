package com.surkhojb.groceryapp.feature.main.adapter

import com.surkhojb.groceryapp.model.GroceryItem

interface GroceriesOnItemCheckListener{
    fun onItemChecked(item: GroceryItem)
}