package com.vaasugambhir.furniturekart.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): LiveData<List<ProductEntity>>
}