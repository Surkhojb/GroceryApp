package com.surkhojb.groceryapp.feature.login.view

import androidx.lifecycle.ViewModel
import com.surkhojb.groceryapp.data.repository.LoginRepository
import com.surkhojb.groceryapp.model.User

class LoginViewModel(private val repository: LoginRepository): ViewModel(){

    fun loginUser(user: User){
        repository.firebaseLogin(user)
    }

    fun singUpUser(user: User){
        repository.firebaseSingUp(user)
    }
}