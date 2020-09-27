package com.surkhojb.groceryapp.feature.common.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.surkhojb.groceryapp.feature.common.CustomDialog
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseViewModelActivity<VM: ViewModel>(private val viewModelClass: KClass<VM>): BaseActivity() {
    protected var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModule()
        viewModel = getViewModel(viewModelClass)
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadModule()
    }

    //We call this function to setUp our observers
    abstract fun observeViewModel()

    //We call the function when we need load a independent module
    abstract fun injectModule()

    //We call the function when we need clear a independent module
    abstract fun unloadModule()
}