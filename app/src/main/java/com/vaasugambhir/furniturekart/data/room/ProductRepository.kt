package com.vaasugambhir.furniturekart.data.room

import android.app.Application

class ProductRepository(application: Application) {
    private val productDao: ProductDao

    init {
        val database = ProductDatabase.getDatabase(application)
        productDao = database.productDao()
    }

    suspend fun insert(product: ProductEntity) = productDao.insert(product)

    suspend fun delete(product: ProductEntity) = productDao.delete(product)

    fun getAllProducts() = productDao.getAllProducts()
}