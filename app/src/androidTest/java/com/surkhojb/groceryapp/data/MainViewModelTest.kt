package com.surkhojb.groceryapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.surkhojb.groceryapp.data.db.GroceryDatabase
import com.surkhojb.groceryapp.data.repository.impl.GroceryRepositoryImpl
import com.surkhojb.groceryapp.feature.main.viewmodel.MainViewModel
import com.surkhojb.groceryapp.model.GroceryItem
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var database: GroceryDatabase

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            GroceryDatabase::class.java).build()

        val repository = GroceryRepositoryImpl(database)
        viewModel = MainViewModel(repository)
    }

    @After
    fun closeDb(){
        database.close()
    }

    @Test
    fun whenDatabaseIsEmptyShouldReturnEmptyList(){
        val list = viewModel.getItems().getValueBlocking()
        assertEquals(list, emptyList<GroceryItem>())
    }

    @Test
    fun afterInsertOneItemShouldReturnANonEmptyList(){
        val item = mockGroceryItem()
        runBlocking {
            viewModel.insertItem(item)
        }

        val list = viewModel.getItems().getValueBlocking()
        assertNotEquals(list, emptyList<GroceryItem>())
    }

    @Test
    fun afterUpdateItemShouldReturnListWithItemUpdated(){
        val item = mockGroceryItem()

        runBlocking {
            viewModel.insertItem(item)

            val itemAdded =  viewModel.getItems().getValueBlocking()?.get(0)
            itemAdded?.name = "Bread"
            itemAdded?.amount = 1
            itemAdded?.checked = true

            viewModel.updateItem(itemAdded!!)
        }

        val list = viewModel.getItems().getValueBlocking()
        val itemUpdated = list.let { list?.get(0) }

        assertEquals(GroceryItem("Bread",1,checked = true), itemUpdated )
    }

    @Test
    fun afterDeleteLastItemShouldReturnEmptyList(){
        val item = GroceryItem("Bread",1)
        item.id = 1
        runBlocking {
            viewModel.insertItem(item)
            viewModel.deleteItem(item)
        }

        val list = viewModel.getItems().getValueBlocking()
        assertEquals(list, emptyList<GroceryItem>())
    }


    private fun mockGroceryItem() = GroceryItem("Bananas",3)
}