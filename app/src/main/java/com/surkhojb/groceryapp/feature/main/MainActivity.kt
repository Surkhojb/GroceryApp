package com.surkhojb.groceryapp.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.main.adapter.GroceriesAdapter
import com.surkhojb.groceryapp.feature.main.adapter.GroceriesOnItemCheckListener
import com.surkhojb.groceryapp.feature.main.viewmodel.MainViewModel
import com.surkhojb.groceryapp.model.GroceryItem
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), GroceriesOnItemCheckListener {

    private val viewModel: MainViewModel by viewModel()
    lateinit var rvGroceries: RecyclerView
    private val adapter = GroceriesAdapter()
    lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFabButton()
        setUpRecyclerView()
        observeViewModel()
    }

    //RecyclerView item checked
    override fun onItemChecked(item: GroceryItem) {
        viewModel.updateItem(item)
    }

    private fun setUpFabButton(){
        extended_fab.setOnClickListener {
            viewModel.insertItem(GroceryItem("Banana",1))
        }
    }

    private fun setUpRecyclerView() {
        rootView = findViewById(R.id.root_view)
        rvGroceries = findViewById(R.id.rv_grocery_list)
        rvGroceries.layoutManager = LinearLayoutManager(this)
        rvGroceries.hasFixedSize()
        rvGroceries.adapter = adapter
        adapter.setOnCheckListener(this)
    }

    private fun observeViewModel(){
        viewModel.getItems().observe(this, Observer {
            if(it.isNullOrEmpty()) {
                Snackbar.make(
                    rootView,
                    "Error: Something happened fetching your list... ",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                adapter.refreshData(it)
            }
        })
    }
}
