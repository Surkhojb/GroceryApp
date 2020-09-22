package com.surkhojb.groceryapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.surkhojb.groceryapp.data.db.GroceryDatabase
import com.surkhojb.groceryapp.model.GroceryItem
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GroceryDaoTest{
    private lateinit var database: GroceryDatabase

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupDb(){
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
            GroceryDatabase::class.java).build()
    }

    @After
    fun closeDb(){
        database.close()
    }

    @Test
    fun afterInsertItemDbShouldNotBeEmpty(){
        database.getGroceryDao().insertItem(mockGroceryItem())
        val items = database.getGroceryDao().getItems().getValueBlocking()
        assertTrue(items.let { it!!.isNotEmpty()})
    }

    @Test
    fun afterInsertItemsWeShouldGetSameList(){
        val initialItems = mockGroceryItems(5)
        initialItems.forEach {
            database.getGroceryDao().insertItem(it)
        }

        val items = database.getGroceryDao().getItems().getValueBlocking()
        assertTrue(items == initialItems.sortedWith(compareBy({ a -> a.id }, { b -> b.id })))
    }

    @Test
    fun afterDeleteItemItShouldNotBeAtItemsFetched(){
        val item = mockGroceryItem()
        item.id = 0
        val item2 = mockGroceryItem()
        item2.id = 1
        val id = item.id

        database.getGroceryDao().insertItem(item)
        database.getGroceryDao().insertItem(item2)
        database.getGroceryDao().deleteItem(item)

        val items = database.getGroceryDao().getItems().getValueBlocking()
        assertTrue(items.let { items!!.none { it.id == id }})

    }

    @Test
    fun afterUpdateItemShouldNotBeTheSame(){
        val item = GroceryItem("Banana",1)
        database.getGroceryDao().insertItem(item)
        val itemAdded =  database.getGroceryDao().getItems().getValueBlocking()?.get(0)
        itemAdded?.checked = true
        database.getGroceryDao().updateItem(itemAdded!!)

        val items = database.getGroceryDao().getItems().getValueBlocking()
        assertTrue(items.let { items?.get(0)?.checked == true})
    }

    @Test
    fun afterDeleteShouldReturnEmptyList(){
        val item = mockGroceryItem()
        database.getGroceryDao().insertItem(item)

        database.getGroceryDao().clear()
        val items = database.getGroceryDao().getItems().getValueBlocking()
        assertTrue(items.isNullOrEmpty())
    }

    private fun mockGroceryItem() = GroceryItem("Bananas",3)

    private fun mockGroceryItems(amount: Int): List<GroceryItem>{
        val list = arrayListOf<GroceryItem>()
        for(x in 0..amount){
            list.add(GroceryItem("Item",1))
        }
        return list
    }
}