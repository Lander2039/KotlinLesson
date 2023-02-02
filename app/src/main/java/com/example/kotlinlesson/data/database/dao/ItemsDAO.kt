package com.example.kotlinlesson.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.kotlinlesson.data.database.FavoritesEntity
import com.example.kotlinlesson.data.database.ItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDAO {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT * From itemsEntity ")
    fun getItemsEntities(): Flow<List<ItemsEntity>>

    @Query("SELECT (SELECT COUNT(*) FROM itemsEntity) != 0")
    fun doesItemsEntityExist(): Boolean

    @Query("DELETE FROM itemsEntity WHERE description =:description")
    fun deleteItemEntityByDescription(description: String)

    @Query("SELECT * FROM itemsEntity WHERE description = :searchText")
    fun findItemEntityByDescription(searchText: String): ItemsEntity


    @Insert(onConflict = IGNORE) // игнорирование если они одинаковы
    fun insertFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favoritesEntity")
    fun getFavoritesEntity(): List<FavoritesEntity>
}