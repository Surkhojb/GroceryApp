package com.surkhojb.groceryapp.feature.login.view

import androidx.core.content.ContextCompat
import com.surkhojb.groceryapp.R
import com.surkhojb.groceryapp.di.loginModule
import com.surkhojb.groceryapp.feature.common.CustomDialog
import com.surkhojb.groceryapp.feature.common.OnButtonClickListener
import com.surkhojb.groceryapp.feature.common.base.BaseActivity
import com.surkhojb.groceryapp.feature.common.base.Validator
import com.surkhojb.groceryapp.model.User
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class LoginActivity: BaseActivity<LoginViewModel>(LoginViewModel::class) {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun setUpComponents() {
        setUpLoginButton()
        setUpSingUpButton()
    }

    private fun setUpLoginButton(){
        btn_login.setOnClickListener {
            val user = getUser()
            user?.let {
                viewModel.loginUser(it)
            }
        }
    }

    private fun setUpSingUpButton(){
        btn_singup.setOnClickListener {
            val user = getUser()
            user?.let {
                viewModel.singUpUser(it)
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
            showDialog(CustomDialog.Type.SUCCESS,
                "Uppss... something it's wrong",
                validator.second)
            null
        }
    }

    override fun injectModule() {
      loadKoinModules(loginModule)
    }

    override fun unloadModule() {
        unloadKoinModules(loginModule)
    }


}