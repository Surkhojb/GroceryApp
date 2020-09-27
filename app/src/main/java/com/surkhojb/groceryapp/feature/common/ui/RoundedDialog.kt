package com.surkhojb.groceryapp.feature.common.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.model.GroceryItem
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

interface OnAddItemClickListener{
    fun onAddClick(groceryItem: GroceryItem)
}

class RoundedDialog: DialogFragment() {
    private val TAG = RoundedDialog::class.simpleName
    private var onAddItemClickListener: OnAddItemClickListener? = null
    private lateinit var edName: TextInputEditText
    private lateinit var edAmount: TextInputEditText
    private lateinit var  btnAdd: Button

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT ,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_item_dialog_fragment,container,false)
        edName = view.findViewById(R.id.ed_item_name)
        edAmount = view.findViewById(R.id.ed_item_amount)
        btnAdd =  view.findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            if(validateValues(edName.text.toString(),edAmount.text.toString())){
                try{
                    val name = edName.text.toString()
                    val amount = edAmount.text.toString().toInt()
                    onAddItemClickListener.let {
                        it?.onAddClick(GroceryItem(name,amount))
                        this.dismiss()
                    }
                }catch (ex: NumberFormatException){
                    Log.e(TAG, ex.printStackTrace().toString())
                }
            }else{
                edName.error = getString(R.string.error_item_empty)
                edAmount.error = getString(R.string.error_item_empty)
            }
        }
        return view
    }

    private fun validateValues(name: String?, amount: String?): Boolean {
        return !name.isNullOrEmpty() || !amount.isNullOrEmpty()
    }

    fun setOnAddItemClickListener(listener: OnAddItemClickListener?){
        if (listener == null)
            throw IllegalArgumentException("Listener can't be null")

        onAddItemClickListener = listener
    }
}