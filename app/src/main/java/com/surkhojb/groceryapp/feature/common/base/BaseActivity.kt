package com.surkhojb.groceryapp.feature.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.CustomDialog
import com.surkhojb.groceryapp.feature.common.OnButtonClickListener
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM: ViewModel>(private val viewModelClass: KClass<VM>?): AppCompatActivity() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModule()
        //We only inject viewModel if the class is not null
        if(viewModelClass != null){
            viewModel = getViewModel(viewModelClass)
        }
        setContentView(getLayoutId())
        setUpComponents()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadModule()
    }

    fun showDialog(type: CustomDialog.Type, title: String, message: String){
       when(type){
           CustomDialog.Type.ERROR -> { showError(title,message) }
           CustomDialog.Type.INFO -> { showInfo(title,message) }
           CustomDialog.Type.SUCCESS -> { showSuccess(title,message) }
       }
    }

    abstract fun getLayoutId(): Int

    abstract fun setUpComponents()

    //We call the function when we need load a independent module
    abstract fun injectModule()

    //We call the function when we need clear a independent module
    abstract fun unloadModule()

    //region Private area
    private fun showError(title: String, message:String){
        CustomDialog.Builder()
            .background(ContextCompat.getColor(applicationContext, R.color.colorError))
            .icon(R.drawable.ic_error)
            .title(title)
            .message(message)
            .buttonText("Accept")
            .setListener(object : OnButtonClickListener {
                override fun onClick(customDialog: CustomDialog) {
                    customDialog.dismiss()
                }

            }).build()
            .show(supportFragmentManager, null)
    }

    private fun showSuccess(title: String, message:String){
        CustomDialog.Builder()
            .background(ContextCompat.getColor(applicationContext, R.color.colorSuccess))
            .icon(R.drawable.ic_success)
            .title(title)
            .message(message)
            .buttonText("Accept")
            .setListener(object : OnButtonClickListener {
                override fun onClick(customDialog: CustomDialog) {
                    customDialog.dismiss()
                }

            }).build()
            .show(supportFragmentManager, null)
    }

    private fun showInfo(title: String, message:String){
        CustomDialog.Builder()
            .background(ContextCompat.getColor(applicationContext, R.color.colorInfo))
            .icon(R.drawable.ic_info)
            .title(title)
            .message(message)
            .buttonText("Accept")
            .setListener(object : OnButtonClickListener {
                override fun onClick(customDialog: CustomDialog) {
                    customDialog.dismiss()
                }

            }).build()
            .show(supportFragmentManager, null)
    }
    //endregion
}