package com.surkhojb.groceryapp.data.repository

import com.google.firebase.auth.FirebaseUser
import com.surkhojb.groceryapp.feature.common.base.Response
import com.surkhojb.groceryapp.model.User

interface LoginRepository {
   suspend fun firebaseLogin(user: User): Response<FirebaseUser>

    suspend fun firebaseSingUp(user:User): Response<FirebaseUser>
}