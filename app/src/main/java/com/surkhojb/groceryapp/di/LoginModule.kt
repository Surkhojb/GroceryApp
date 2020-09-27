package com.surkhojb.groceryapp.di

import com.google.firebase.auth.FirebaseAuth
import com.surkhojb.groceryapp.data.repository.LoginRepository
import com.surkhojb.groceryapp.data.repository.impl.LoginRepositoryImpl
import com.surkhojb.groceryapp.feature.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {


    single { getFirebaseAuth()}

    single { getRepository(get()) }

    viewModel { getLoginViewModel(get()) }

}

fun getFirebaseAuth(): FirebaseAuth {
    return FirebaseAuth.getInstance()
}

fun getRepository(firebaseAuth: FirebaseAuth): LoginRepository {
    return LoginRepositoryImpl(firebaseAuth)
}

fun getLoginViewModel(repository: LoginRepository): LoginViewModel {
    return LoginViewModel(
        repository
    )
}


/*
val netWorkModule = module{
    single { getGitBrowserApi(get()) }
    single { getRepository(get())}
}

fun getGitBrowserApi(retrofit: Retrofit): GitBrowserApi{
    return  retrofit.create(GitBrowserApi::class.java)
}

fun getRepository(gitBrowserApi: GitBrowserApi): GitBrowserRepository{
    return GitBrowserRepositoryImpl(gitBrowserApi)
}
* */