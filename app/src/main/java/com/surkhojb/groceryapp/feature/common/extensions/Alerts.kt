package com.surkhojb.groceryapp.feature.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackShort(message:String){
    snack(this,message,Snackbar.LENGTH_SHORT)
}

fun View.snackLong(message:String, duration: Int){
    snack(this,message,Snackbar.LENGTH_LONG)
}

private fun snack(view: View,message:String, duration: Int){
    Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}