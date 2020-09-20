package com.surkhojb.groceryapp.data.repository.impl

import androidx.lifecycle.LiveData
import com.surkhojb.groceryapp.data.db.GroceryDatabase
import com.surkhojb.groceryapp.model.GroceryItem

class GroceryRepositoryImpl (val database: GroceryDatabase): GroceryRepository {
    private val localDao = database.getGroceryDao()
    override fun getItems(): LiveData<List<GroceryItem>> {
        return localDao.getItems()
    }

    override suspend fun saveItem(item: GroceryItem) {
        return localDao.insertItem(item)
    }

    override suspend fun deleteItem(item: GroceryItem) {
        return localDao.deleteItem(item)
    }

    override suspend fun updateItem(item: GroceryItem) {
        return localDao.updateItem(item)
    }
}