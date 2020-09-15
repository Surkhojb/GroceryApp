package com.surkhojb.groceryapp.model

data class ShoppingItem(
    var name: String,
    var amount: Int,
    var category: String = ""
)