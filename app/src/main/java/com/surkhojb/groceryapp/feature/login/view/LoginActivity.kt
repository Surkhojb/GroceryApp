package com.surkhojb.groceryapp.feature.login.view

import androidx.lifecycle.Observer
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.di.loginModule
import com.surkhojb.groceryapp.feature.common.ui.CustomDialog
import com.surkhojb.groceryapp.feature.common.base.BaseViewModelActivity
import com.surkhojb.groceryapp.feature.common.base.Response
import com.surkhojb.groceryapp.feature.common.base.Validator
import com.surkhojb.groceryapp.feature.login.viewmodel.LoginViewModel
import com.surkhojb.groceryapp.feature.main.MainActivity
import com.surkhojb.groceryapp.model.User
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class LoginActivity: BaseViewModelActivity<LoginViewModel>(
    LoginViewModel::class) {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun setUpComponents() {
        setUpLoginButton()
        setUpSingUpButton()
    }

    override fun observeViewModel(){
        viewModel?.loginResult?.observe(this, Observer {
            when(it){
                is Response.success -> { MainActivity.start(this) }
                is Response.error -> {  showDialog(
                    CustomDialog.Type.ERROR,
                    getString(R.string.error_something_wrong),
                    it.errorMessage.toString())}
                is Response.loading -> { showLoading() }
            }
        })

        viewModel?.singUpResult?.observe(this, Observer {
            when(it){
                is Response.success -> { MainActivity.start(this) }
                is Response.error -> {  showDialog(
                    CustomDialog.Type.ERROR,
                    getString(R.string.error_something_wrong),
                    it.errorMessage.toString())}
                is Response.loading -> { showLoading() }
            }
        })
    }

    override fun injectModule() {
      loadKoinModules(loginModule)
    }

    override fun unloadModule() {
        unloadKoinModules(loginModule)
    }

    private fun setUpLoginButton(){
        btn_login.setOnClickListener {
            val user = getUser()
            user?.let {
                viewModel?.loginUser(it)
            }
        }
    }

    private fun setUpSingUpButton(){
        btn_singup.setOnClickListener {
            val user = getUser()
            user?.let {
                viewModel?.singUpUser(it)
            }
        }
    }

    private fun getUser(): User? {
        val user = User()
        val validator = Validator.validateLoginInputs(ed_email,ed_pass)
        return if(validator.first){
            user.userEmail = ed_email.text.toString()
            user.userPassword = ed_pass.text.toString()
            user
        }else{
            showDialog(
                CustomDialog.Type.ERROR,
                getString(R.string.error_something_wrong),
                validator.second)
            null
        }
    }

}