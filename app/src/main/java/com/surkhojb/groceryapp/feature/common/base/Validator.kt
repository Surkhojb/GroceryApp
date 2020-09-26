package com.surkhojb.groceryapp.feature.common.base

import android.widget.EditText
import com.surkhojb.groceryapp.feature.common.extensions.validEmail
import com.surkhojb.groceryapp.feature.common.extensions.validPassword
import java.lang.IllegalArgumentException

object Validator {
    fun validateLoginInputs(email: EditText,password: EditText): Pair<Boolean,String>{
       return when(email.validEmail() to password.validPassword()){
            true to true -> Pair(true,"")
            true to false -> Pair(false,"Check your password: Password must be between 4 and 8 digits long and include at least a numeric digit")
            false to true -> Pair(false,"Check your email: Email should contain @ and be a valid email")
            false to false -> Pair(false,"Password must be between 4 and 8 digits long and include at least a numeric digit\n Email should contain '@' and be a valid email ")
            else -> throw IllegalArgumentException("Error: something went wrong with validator.")
        }
    }
}