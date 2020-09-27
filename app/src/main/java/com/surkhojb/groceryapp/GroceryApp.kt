package com.surkhojb.groceryapp

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.surkhojb.groceryapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GroceryApp(): Application() {


    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@GroceryApp)
            modules(databaseModule,repositoryModule,viewModelModule)
        }
    }

    companion object{
        private lateinit var context: Context
       fun getContext(): Context = context
    }
}