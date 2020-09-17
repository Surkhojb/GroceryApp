package com.surkhojb.groceryapp.di

import android.app.Application
import com.surkhojb.groceryapp.data.db.GroceryDatabase
import com.surkhojb.groceryapp.data.repository.impl.GroceryRepository
import com.surkhojb.groceryapp.data.repository.impl.GroceryRepositoryImpl
import com.surkhojb.groceryapp.feature.main.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val databaseModule = module {
    fun getDatabase(application: Application): GroceryDatabase {
        return GroceryDatabase.getInstance(application)!!
    }

    single { getDatabase(androidApplication()) }
}

val repositoryModule = module {
    fun getRepository(database: GroceryDatabase): GroceryRepository {
        return GroceryRepositoryImpl(database)
    }
    single { getRepository(get()) }

}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

}