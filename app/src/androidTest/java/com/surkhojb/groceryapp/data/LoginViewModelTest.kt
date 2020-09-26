package com.surkhojb.groceryapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.auth.FirebaseAuth
import com.surkhojb.groceryapp.data.repository.impl.LoginRepositoryImpl
import com.surkhojb.groceryapp.feature.common.base.Response
import com.surkhojb.groceryapp.feature.login.view.LoginViewModel
import com.surkhojb.groceryapp.model.User
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        val firebaseAuth = FirebaseAuth.getInstance()
        viewModel = LoginViewModel(LoginRepositoryImpl(firebaseAuth))
    }

    @Test
    fun whenUserIsNotRegisterShouldReturnErrorMessage(){
        val errorMessage = "There is no user record corresponding to this identifier. The user may have been deleted."
        val user = User("asdf@gmail.com","1234asd")

        runBlocking {
            viewModel.loginUser(user)
        }

        val result = viewModel.loginResult.getValueBlocking()
        assertEquals(Response.error(null,errorMessage), result)

    }

    @Test
    fun whenUserIsAlreadyRegisterShouldReturnErrorMessage(){
        val errorMessage = "The email address is already in use by another account."
        val user = User("surkhojbdev@gmail.com","1234asdf")

        runBlocking {
            viewModel.singUpUser(user)
        }

        val result = viewModel.singUpResult.getValueBlocking()
        assertEquals(Response.error(null,errorMessage), result)

    }

    @Test
    fun whenUserPasswordIsWrongShouldReturnErrorMessage(){
        val errorMessage = "The password is invalid or the user does not have a password."
        val user = User("surkhojbdev@gmail.com","asdf")

        runBlocking {
            viewModel.loginUser(user)
        }

        val result = viewModel.loginResult.getValueBlocking()
        assertEquals(Response.error(null,errorMessage), result)

    }

    @Test
    fun whenUserPasswordIsWrongShouldReturnSuccessData(){
        val userEmail = "surkhojbdev@gmail.com"
        val user = User("surkhojbdev@gmail.com","1234asdf")

        runBlocking {
            viewModel.loginUser(user)
        }

        val result = viewModel.loginResult.getValueBlocking()
        assertEquals(userEmail,result?.data?.email.toString())

    }
}