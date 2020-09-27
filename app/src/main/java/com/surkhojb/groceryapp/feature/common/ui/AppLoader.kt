package com.surkhojb.groceryapp.feature.common.ui

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.surkhojb.groceryapp.R

class AppLoader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
):FrameLayout(context, attrs, defStyleAttr, defStyleRes){

    init {
        initLoader()
    }

    private fun initLoader(){
        visibility = View.GONE
        setBackgroundColor(ContextCompat.getColor(context,R.color.colorLight))
        elevation = 100f
        val progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        val drawableProgress =
            DrawableCompat.wrap(progressBar.indeterminateDrawable)
        DrawableCompat.setTint(
            drawableProgress,
            ContextCompat.getColor(context, android.R.color.black)
        )
        progressBar.indeterminateDrawable = DrawableCompat.unwrap(
            drawableProgress
        )
        addView(progressBar, LayoutParams(LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,Gravity.CENTER))
    }

    fun targetView(view: Activity){
        //Each activity should implement container id as rootView, to attach our loader
        try {
            val viewGroup =
                (view.findViewById(R.id.container) as ViewGroup)

            val loaderParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            viewGroup.addView(this, -1, loaderParams)
        } catch (ex: Exception) {
            val errorMessage = when(ex){
                is TypeCastException -> {
                    "${view.localClassName} should implement '@+id/container' as root view to attach our loader"
                }
                else -> ex.message.toString()
            }
            Log.e("AppLoader: ",errorMessage)
        }
    }

    fun showLoader() {
        visibility = View.VISIBLE
    }

    fun hideLoader() {
        visibility = View.GONE
    }

    fun isShowing(): Boolean{
        return visibility == View.VISIBLE
    }
}