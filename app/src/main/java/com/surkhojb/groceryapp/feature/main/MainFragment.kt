package com.surkhojb.groceryapp.feature.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.ui.OnAddItemClickListener
import com.surkhojb.groceryapp.feature.common.ui.RoundedDialog
import com.surkhojb.groceryapp.feature.common.ui.SwipeCallBack
import com.surkhojb.groceryapp.feature.common.base.BaseFragment
import com.surkhojb.groceryapp.feature.common.extensions.snackShort
import com.surkhojb.groceryapp.feature.main.adapter.GroceriesAdapter
import com.surkhojb.groceryapp.feature.main.adapter.GroceriesOnItemCheckListener
import com.surkhojb.groceryapp.feature.main.viewmodel.MainViewModel
import com.surkhojb.groceryapp.model.GroceryItem
import kotlinx.android.synthetic.main.main_content_fragment.view.*

class MainFragment: BaseFragment<MainViewModel>(MainViewModel::class),
    GroceriesOnItemCheckListener {
    lateinit var rvGroceries: RecyclerView
    private val adapter = GroceriesAdapter()
    lateinit var rootView: View
    lateinit var emptyView: View

    override fun getLayoutId(): Int {
        return R.layout.main_content_fragment
    }

    override fun setUpComponents(view: View) {
        setUpFabButton(view)
        setUpRecyclerView(view)
        observeViewModel()
    }

    //RecyclerView item checked
    override fun onItemChecked(item: GroceryItem) {
        viewModel.updateItem(item)
    }

    private fun setUpFabButton(view: View) {
        view.fab_add.setOnClickListener {
            val dialog =
                RoundedDialog()
            dialog.setOnAddItemClickListener(object :
                OnAddItemClickListener {
                override fun onAddClick(groceryItem: GroceryItem) {
                    viewModel.insertItem(groceryItem)
                }
            })
            dialog.show(activity?.supportFragmentManager!!, null)
        }
    }


    private fun setUpRecyclerView(view: View) {
        rootView = view.findViewById(R.id.root_view)
        emptyView = view.findViewById(R.id.empty_view)
        rvGroceries = view.findViewById(R.id.rv_grocery_list)
        rvGroceries.layoutManager = LinearLayoutManager(context)
        rvGroceries.hasFixedSize()
        rvGroceries.adapter = adapter
        adapter.setOnCheckListener(this)
        val touchHelper = ItemTouchHelper(object : SwipeCallBack(requireContext()) {
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
                    view.fab_add.show()
                } else{
                    view.fab_add.hide()
                }
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    view.fab_add.show()
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

    override fun injectModule() {}

    override fun unloadModule() {}
}