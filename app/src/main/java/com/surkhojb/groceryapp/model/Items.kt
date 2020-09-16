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
    var category: String = "",
    @ColumnInfo(name = "item_checked")
    var checked: Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}