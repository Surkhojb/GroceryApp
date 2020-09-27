package com.surkhojb.groceryapp.feature.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.ui.AppLoader
import com.surkhojb.groceryapp.feature.common.ui.CustomDialog
import com.surkhojb.groceryapp.feature.common.ui.OnButtonClickListener
abstract class BaseActivity: AppCompatActivity() {

    private lateinit var loader: AppLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setUpLoader()
        setUpComponents()
    }

    open fun showDialog(type: CustomDialog.Type, title: String, message: String){
       if( loader.isShowing()) { hideLoader() }
       when(type){
           CustomDialog.Type.ERROR -> { showError(title,message) }
           CustomDialog.Type.INFO -> { showInfo(title,message) }
           CustomDialog.Type.SUCCESS -> { showSuccess(title,message) }
       }
    }

    open fun showLoading(){
        loader.showLoader()
    }

    open fun hideLoader(){
        loader.hideLoader()
    }

    abstract fun getLayoutId(): Int

    abstract fun setUpComponents()

    //region Private area
    private fun setUpLoader(){
        loader = AppLoader(this)
        loader.targetView(this)
    }

    private fun showError(title: String, message:String){
        CustomDialog.Builder()
            .background(ContextCompat.getColor(applicationContext, R.color.colorError))
            .icon(R.drawable.ic_error)
            .title(title)
            .message(message)
            .buttonText("Accept")
            .setListener(object :
                OnButtonClickListener {
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
            .setListener(object :
                OnButtonClickListener {
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
            .setListener(object :
                OnButtonClickListener {
                override fun onClick(customDialog: CustomDialog) {
                    customDialog.dismiss()
                }

            }).build()
            .show(supportFragmentManager, null)
    }
    //endregion
}