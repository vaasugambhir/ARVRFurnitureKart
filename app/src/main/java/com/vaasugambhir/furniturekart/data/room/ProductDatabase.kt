package com.vaasugambhir.furniturekart.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var instance: ProductDatabase? = null
        fun getDatabase(context: Context): ProductDatabase {
            val temp = instance
            if (temp != null) return temp
            else {
                synchronized(this) {
                    val inst = Room.databaseBuilder(
                        context,
                        ProductDatabase::class.java,
                        "product_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    instance = inst
                    return inst
                }
            }
        }
    }
}