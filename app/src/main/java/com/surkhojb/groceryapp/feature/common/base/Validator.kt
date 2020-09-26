package com.surkhojb.groceryapp.feature.common.base

import android.content.res.Resources
import android.widget.EditText
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.feature.common.extensions.validEmail
import com.surkhojb.groceryapp.feature.common.extensions.validPassword
import java.lang.IllegalArgumentException

object Validator {
    val resources = Resources.getSystem()

    fun validateLoginInputs(email: EditText,password: EditText): Pair<Boolean,String>{
       return when(email.validEmail() to password.validPassword()){
            true to true -> Pair(true,"")
            true to false -> Pair(false, resources.getString(R.string.error_invalid_password_format))
            false to true -> Pair(false,resources.getString(R.string.error_invalid_email_format))
            false to false -> Pair(false, resources.getString(R.string.error_invalid_email_and_password_format))
            else -> throw IllegalArgumentException("Error: something went wrong with validator.")
        }
    }
}