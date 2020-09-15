package com.surkhojb.groceryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_list")
data class GroceryItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @ColumnInfo(name = "item_category")
    var category: String = ""
){
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}