package com.surkhojb.groceryapp.feature.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.surkhojb.groceryapp.data.repository.LoginRepository
import com.surkhojb.groceryapp.feature.common.base.Response
import com.surkhojb.groceryapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository): ViewModel(){
    var loginResult : MutableLiveData<Response<FirebaseUser>> = MutableLiveData()
    var singUpResult : MutableLiveData<Response<FirebaseUser>> = MutableLiveData()

    fun loginUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        loginResult.postValue(Response.loading)
        val result = repository.firebaseLogin(user)
        loginResult.postValue(result)
    }

    fun singUpUser(user: User) = CoroutineScope(Dispatchers.IO).launch {
        val result =  repository.firebaseSingUp(user)
        singUpResult.postValue(result)
    }
}