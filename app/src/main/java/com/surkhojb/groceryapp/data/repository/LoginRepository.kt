package com.surkhojb.groceryapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.surkhojb.groceryapp.model.User

interface LoginRepository {
    fun firebaseLogin(user: User): MutableLiveData<FirebaseUser>

    fun firebaseSingUp(user:User): MutableLiveData<FirebaseUser>
}