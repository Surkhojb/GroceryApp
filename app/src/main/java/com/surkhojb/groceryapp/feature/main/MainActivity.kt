package com.surkhojb.groceryapp.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.base.BaseActivity
import com.surkhojb.groceryapp.feature.main.viewmodel.MainViewModel


class MainActivity : BaseActivity<MainViewModel>(viewModelClass = MainViewModel::class) {

    override fun getLayoutId(): Int{
        return R.layout.activity_main
    }

    override fun setUpComponents() {}

    override fun injectModule() {}

    override fun unloadModule() {}

    companion object {
        fun start(from: Context){
            val intent = Intent(from, MainActivity::class.java)
            from.startActivity(intent)
        }
    }
}
