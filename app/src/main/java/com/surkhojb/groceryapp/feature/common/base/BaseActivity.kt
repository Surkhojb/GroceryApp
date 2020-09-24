package com.surkhojb.groceryapp.feature.common.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM: ViewModel>(private val viewModelClass: KClass<VM>): AppCompatActivity() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModule()
        viewModel = getViewModel(viewModelClass)
        setContentView(getLayoutId())
        setUpComponents()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadModule()
    }

    abstract fun getLayoutId(): Int

    abstract fun setUpComponents()

    //We call the function when we need load a independent module
    protected fun injectModule(){}

    //We call the function when we need clear a independent module
    protected fun unloadModule(){}
}