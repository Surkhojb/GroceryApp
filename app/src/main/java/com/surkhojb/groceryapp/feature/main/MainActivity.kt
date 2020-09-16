package com.surkhojb.groceryapp.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.main.adapter.GroceriesAdapter
import com.surkhojb.groceryapp.feature.main.adapter.GroceriesOnItemCheckListener
import com.surkhojb.groceryapp.feature.main.viewmodel.MainViewModel
import com.surkhojb.groceryapp.model.GroceryItem

class MainActivity : AppCompatActivity(), GroceriesOnItemCheckListener {

    lateinit var viewModel: MainViewModel
    lateinit var rvGroceries: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewModel = ViewModelProviders.of(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        rvGroceries = findViewById(R.id.rv_grocery_list)
        rvGroceries.layoutManager = LinearLayoutManager(this)
        rvGroceries.hasFixedSize()

        val adapter = GroceriesAdapter()
        adapter.setOnCheckListener(this)
        rvGroceries.adapter = adapter
    }

    //RecyclerView item checked
    override fun onItemChecked(item: GroceryItem) {
        //TODO: UPDATE DB WITH CHECKED OR UNCHECKED ITEM
    }
}
