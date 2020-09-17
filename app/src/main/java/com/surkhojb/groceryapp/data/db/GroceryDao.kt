package com.surkhojb.groceryapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surkhojb.groceryapp.model.GroceryItem

@Dao
interface GroceryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: GroceryItem)

    @Delete
    fun deleteItem(item: GroceryItem)

    @Query("SELECT * FROM grocery_list")
    fun getItems(): LiveData<List<GroceryItem>>

    @Update
    fun updateItem(item: GroceryItem)
}