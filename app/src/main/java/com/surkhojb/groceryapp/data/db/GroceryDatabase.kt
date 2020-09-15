package com.surkhojb.groceryapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surkhojb.groceryapp.model.GroceryItem

@Database(entities = arrayOf(GroceryItem::class),version = DATABASE_VERSION)
abstract class GroceryDatabase: RoomDatabase() {

    abstract fun getGroceryDao(): GroceryDao

    //Singleton pattern to get database
    companion object{
        @Volatile
        private var instance: GroceryDatabase? = null

        fun getInstance(context: Context): GroceryDatabase? {
            if(instance == null){
                synchronized(this){
                    instance = buildDb(context)
                }
            }

            return instance
        }

        private fun buildDb(context: Context): GroceryDatabase {
            return Room.databaseBuilder(context,GroceryDatabase::class.java,DATABASE_NAME).build()
        }

    }
}

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "grocery_list.db"
