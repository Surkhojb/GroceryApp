package com.surkhojb.groceryapp.data.repository.impl

import androidx.lifecycle.LiveData
import com.surkhojb.groceryapp.model.GroceryItem

interface GroceryRepository {

    suspend fun saveItem(item: GroceryItem)

    suspend fun deleteItem(item: GroceryItem)

    suspend fun updateItem(item: GroceryItem)

    fun getItems(): LiveData<List<GroceryItem>>
}