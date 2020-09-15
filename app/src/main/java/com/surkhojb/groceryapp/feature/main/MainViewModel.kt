package com.surkhojb.groceryapp.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surkhojb.groceryapp.data.repository.impl.GroceryRepository
import com.surkhojb.groceryapp.model.GroceryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (val repository: GroceryRepository): ViewModel(){
    var items: MutableLiveData<List<GroceryItem>> = MutableLiveData()

    fun insertItem(item: GroceryItem) = CoroutineScope(Dispatchers.Main).launch{
        repository.saveItem(item)
    }

    fun deleteItem(item: GroceryItem)= CoroutineScope(Dispatchers.Main).launch{
        repository.deleteItem(item)
    }

    fun getItems() = CoroutineScope(Dispatchers.IO).launch{
        items.value = repository.getItems().value
    }
}