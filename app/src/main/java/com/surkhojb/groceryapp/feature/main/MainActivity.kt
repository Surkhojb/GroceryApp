package com.surkhojb.groceryapp.feature.main

import android.content.Context
import android.content.Intent
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int{
        return R.layout.activity_main
    }

    override fun setUpComponents() {}

    companion object {
        fun start(from: Context){
            val intent = Intent(from, MainActivity::class.java)
            from.startActivity(intent)
        }
    }
}
