package com.surkhojb.groceryapp.data.repository.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.surkhojb.groceryapp.data.repository.LoginRepository
import com.surkhojb.groceryapp.model.User

class LoginRepositoryImpl(val firebaseAuth: FirebaseAuth): LoginRepository {

    override fun firebaseLogin(user: User): MutableLiveData<FirebaseUser> {
        val authResult: MutableLiveData<FirebaseUser> = MutableLiveData()
        firebaseAuth.signInWithEmailAndPassword(user.userEmail!!,user.userPassword!!)
            .addOnCompleteListener {
                if (it.isSuccessful && it.result?.user != null){
                    authResult.value = it.result?.user
                }else{
                    Log.e("LoginRepositoryImpl", it.exception?.message ?: "Error during auth")
                }
        }

        return authResult
    }

    override fun firebaseSingUp(user:User): MutableLiveData<FirebaseUser>{
        val singUpResult: MutableLiveData<FirebaseUser> = MutableLiveData()
        firebaseAuth.createUserWithEmailAndPassword(user.userEmail!!,user.userPassword!!)
            .addOnCompleteListener {
                if(it.isSuccessful && it.result?.user != null){
                    singUpResult.value = it.result?.user
                }else{
                    Log.e("LoginRepositoryImpl", it.exception?.message ?: "Error during singUp")
                }
            }
        return singUpResult

    }
}