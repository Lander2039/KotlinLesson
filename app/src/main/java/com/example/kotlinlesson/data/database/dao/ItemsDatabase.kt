package com.example.kotlinlesson.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinlesson.data.database.FavoritesEntity
import com.example.kotlinlesson.data.database.ItemsEntity

@Database(entities = [ItemsEntity::class, FavoritesEntity::class], version = 1, exportSchema = false)
abstract class ItemsDatabase : RoomDatabase() {

    abstract fun getItemsDAO(): ItemsDAO

    companion object {
        private var DB_INSTANCE: ItemsDatabase? = null

        fun getItemsDatabaseInstance(context: Context): ItemsDatabase {
            return DB_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    "Items_DB"
                )
                .fallbackToDestructiveMigration()
//                .addMigrations(MIGRATION_1_TO_2)
                .build()
                .also { DB_INSTANCE = it }
        }
//        val MIGRATION_1_TO_2 = object : Migration(1,2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE itemsEntity RENAME COLUMN imageUrl TO imageURL3")
//            }
//        }
    }


}