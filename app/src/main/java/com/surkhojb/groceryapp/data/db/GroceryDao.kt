package com.surkhojb.groceryapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surkhojb.groceryapp.model.GroceryItem

@Dao
interface GroceryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: GroceryItem)

    @Delete
    suspend fun deleteItem(item: GroceryItem)

    @Query("SELECT * FROM grocery_list")
    suspend fun getItems(): LiveData<GroceryItem>
}