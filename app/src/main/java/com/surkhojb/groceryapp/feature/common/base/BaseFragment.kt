package com.surkhojb.groceryapp.feature.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.getViewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM: ViewModel>(private val viewModelClass: KClass<VM>): Fragment() {

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectModule()
        viewModel = getViewModel(viewModelClass)
        val view = inflater.inflate(getLayoutId(),container,false)
        setUpComponents(view)
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        unloadModule()
    }

    abstract fun getLayoutId(): Int

    abstract fun setUpComponents(view: View)

    //We call the function when we need load a independent module
    abstract fun injectModule()

    //We call the function when we need clear a independent module
    abstract fun unloadModule()
}