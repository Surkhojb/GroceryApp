package com.surkhojb.groceryapp.data.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.surkhojb.groceryapp.data.repository.LoginRepository
import com.surkhojb.groceryapp.feature.common.base.Response
import com.surkhojb.groceryapp.model.User
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class LoginRepositoryImpl(val firebaseAuth: FirebaseAuth): LoginRepository {
    override suspend fun firebaseLogin(user: User): Response<FirebaseUser> {
        return try {
            val response = firebaseAuth.signInWithEmailAndPassword(user.userEmail!!,
                user.userPassword!!
            ).await()
            Response.success(response.user)
        }catch (ex: Exception){
            Response.error(null,ex.message.toString())
        }
    }

    override suspend fun firebaseSingUp(user: User): Response<FirebaseUser> {
        return try {
            val response = firebaseAuth.createUserWithEmailAndPassword(user.userEmail!!,
                user.userPassword!!
            ).await()
            Response.success(response.user)
        }catch (ex: Exception){
            Response.error(null,ex.message.toString())
        }
    }


}