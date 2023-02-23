package com.example.kotlinlesson

import androidx.compose.ui.text.createTextLayoutResult
import com.example.kotlinlesson.data.database.FavoritesEntity
import com.example.kotlinlesson.data.database.ItemsEntity
import com.example.kotlinlesson.data.database.dao.ItemsDAO
import com.example.kotlinlesson.data.items.ItemsRepositoryImpl
import com.example.kotlinlesson.data.model.ItemsResponse
import com.example.kotlinlesson.data.service.ApiService
import com.example.kotlinlesson.data.service.ApiServiceSecond
import com.example.kotlinlesson.domain.items.ItemsRepository
import com.example.kotlinlesson.domain.model.ItemsModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.createTestCoroutineScope
import okhttp3.Response
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class ExampleUnitTest {
    lateinit var itemsRepository: ItemsRepository

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var apiServiceSecond: ApiServiceSecond

    @Mock
    lateinit var itemsDAO: ItemsDAO

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        itemsRepository = ItemsRepositoryImpl(apiService, apiServiceSecond, itemsDAO)
    }

    @Test
    fun `getData request successful`(){
        val response = retrofit2.Response.success(ItemsResponse(listOf()))

        createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenReturn(response)
            itemsRepository.getData()
            verify(apiService, only()).getData()
        }
    }

    @Test
    fun `showData gave from database success`(){
        val itemsEntity = listOf(ItemsEntity(0, ",", "", false))
        runBlocking {
            `when`(itemsDAO.getItemsEntities()).thenReturn(itemsEntity)
            itemsRepository.showData()

            verify(itemsDAO, times(1)).getItemsEntities()
        }
    }

    @Test
    fun `getData request error`(){
        val response = retrofit2.Response.success(ItemsResponse(listOf()))

        createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenThrow(Exception())
            itemsRepository.getData()

            verify(apiService, only()).getData()
        }
    }

    @Test
    fun `showData gave from database error`(){
        val itemsEntity = listOf(ItemsEntity(0, ",", "", false))
        runBlocking {
            `when`(itemsDAO.getItemsEntities()).thenThrow()
            itemsRepository.showData()

            verify(itemsDAO, never()).getItemsEntities()
        }
    }

    @Test
    fun `deleteItemByDescription success`(){
        runBlocking {
            doNothing().`when`(itemsDAO)
                .deleteItemEntityByDescription("descr")
            `when`(itemsDAO.deleteItemEntityByDescription("descr")).thenReturn(Unit)
            itemsRepository.deleteItemByDescription("descr")

            verify(itemsDAO, times(1)).deleteItemEntityByDescription("descr")
        }
    }

    @Test
    fun `getFavorites success`(){
        val favEntity = listOf<FavoritesEntity>()
        runBlocking {
            `when` (itemsDAO.getFavoritesEntity()).thenReturn(listOf())
            itemsRepository.getFavorites()
            verify(itemsDAO, times(1)).getFavoritesEntity()
            assertEquals(favEntity, itemsDAO.getFavoritesEntity())
            assertNotSame(itemsDAO.getItemsEntities(), itemsDAO.getFavoritesEntity())
        }
    }
}