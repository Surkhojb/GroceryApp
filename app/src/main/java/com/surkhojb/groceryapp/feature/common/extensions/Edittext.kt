package com.surkhojb.groceryapp.feature.common.extensions

import android.widget.EditText

//Password must be between 4 and 8 digits long and include at least one numeric digit.
const val DEFAULT_PASSWORD_PATTERN = "^(?=.*\\d).{4,8}\$"
//Email should contain @ and .xxxx
const val DEFAULT_EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

fun EditText.validPassword(pattern: String = DEFAULT_PASSWORD_PATTERN): Boolean {
    return this.text.matches(Regex(pattern))
}

fun EditText.validEmail(pattern: String = DEFAULT_EMAIL_PATTERN): Boolean {
    return this.text.matches(Regex(pattern))
}