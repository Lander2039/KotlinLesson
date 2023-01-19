package com.example.kotlinlesson.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinlesson.data.database.ItemsEntity

@Dao
interface ItemsDAO {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT * From ItemsEntity ")
    fun getItemsEntities(): List<ItemsEntity>

    @Query("SELECT (SELECT COUNT(*) FROM ItemsEntity) != 0")
    fun doesItemsEntityExist(): Boolean

    @Query("DELETE FROM ItemsEntity WHERE description =:description")
    fun deleteItemEntityByDescription(description: String)
}