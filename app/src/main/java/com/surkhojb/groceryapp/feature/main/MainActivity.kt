package com.surkhojb.groceryapp.feature.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.OnAddItemClickListener
import com.surkhojb.groceryapp.feature.common.RoundedDialog
import com.surkhojb.groceryapp.feature.common.SwipeCallBack
import com.surkhojb.groceryapp.feature.common.extensions.snackShort
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
    lateinit var emptyView: View

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

    private fun setUpFabButton() {
        fab_add.setOnClickListener {
            val dialog = RoundedDialog()
            dialog.setOnAddItemClickListener(object : OnAddItemClickListener {
                override fun onAddClick(groceryItem: GroceryItem) {
                    viewModel.insertItem(groceryItem)
                }
            })
            dialog.show(supportFragmentManager, null)
        }
    }


    private fun setUpRecyclerView() {
        rootView = findViewById(R.id.root_view)
        emptyView = findViewById(R.id.empty_view)
        rvGroceries = findViewById(R.id.rv_grocery_list)
        rvGroceries.layoutManager = LinearLayoutManager(this)
        rvGroceries.hasFixedSize()
        rvGroceries.adapter = adapter
        adapter.setOnCheckListener(this)
        val touchHelper = ItemTouchHelper(object : SwipeCallBack(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.getItem(viewHolder.adapterPosition)
                item.let {
                    viewModel.deleteItem(item!!)
                    rootView.snackShort((getString(R.string.item_deleted)))
                }
            }})
        touchHelper.attachToRecyclerView(rvGroceries)

        rvGroceries.setOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy == 0){
                    fab_add.show()
                } else{
                    fab_add.hide()
                }
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab_add.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun observeViewModel(){
        viewModel.getItems().observe(this, Observer {
            if(it.isNullOrEmpty()) {
                emptyView.visibility = View.VISIBLE
                rvGroceries.visibility = View.GONE
            }else{
                emptyView.visibility = View.GONE
                rvGroceries.visibility = View.VISIBLE
                adapter.refreshData(it)
            }
        })
    }
}
