package com.surkhojb.groceryapp.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import com.surkhojb.groceryapp.data.repository.impl.GroceryRepository
import com.surkhojb.groceryapp.model.GroceryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (val repository: GroceryRepository): ViewModel(){

    fun getItems() = repository.getItems()

    fun insertItem(item: GroceryItem) = CoroutineScope(Dispatchers.IO).launch{
        repository.saveItem(item)
    }

    fun updateItem(item: GroceryItem) = CoroutineScope(Dispatchers.IO).launch{
        repository.updateItem(item)
    }

    fun deleteItem(item: GroceryItem)= CoroutineScope(Dispatchers.IO).launch{
        repository.deleteItem(item)
    }


}