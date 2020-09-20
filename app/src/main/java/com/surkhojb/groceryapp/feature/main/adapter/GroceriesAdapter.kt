package com.surkhojb.groceryapp.feature.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.model.GroceryItem
import java.lang.IllegalArgumentException

class GroceriesAdapter: RecyclerView.Adapter<GroceriesAdapter.GroceryViewHolder>() {
    private var groceries: List<GroceryItem>? = null
    private var checkedListener: GroceriesOnItemCheckListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroceriesAdapter.GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grocery,parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceriesAdapter.GroceryViewHolder, position: Int) {
        val item = groceries?.get(position)
        item.let { holder.bind(item!!) }
    }

    override fun getItemCount(): Int {
        if(groceries.isNullOrEmpty())
            return 0

        return groceries!!.count()
    }

    fun setOnCheckListener(listener: GroceriesOnItemCheckListener?){
        if(listener == null)
            throw  IllegalArgumentException("GroceriesOnItemCheckListener can't be null")
        checkedListener = listener
    }

    fun refreshData(items: List<GroceryItem>){
        groceries = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int): GroceryItem?{
        return groceries?.get(position)
    }

    inner class GroceryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemCheck: CheckBox = itemView.findViewById(R.id.item_check)

        fun bind(item: GroceryItem){
            itemName.text = item.name
            itemCheck.isChecked = item.checked
            itemCheck.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val item = groceries?.get(position)
            item.let {
                item!!.checked = itemCheck.isChecked
                checkedListener?.onItemChecked(item)
            }
        }

    }

}