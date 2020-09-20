package com.surkhojb.groceryapp

import android.app.Application
import com.surkhojb.groceryapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GroceryApp(): Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GroceryApp)
            modules(databaseModule,repositoryModule,viewModelModule)
        }
    }
}