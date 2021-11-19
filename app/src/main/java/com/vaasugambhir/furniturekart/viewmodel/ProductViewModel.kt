package com.vaasugambhir.furniturekart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaasugambhir.furniturekart.data.room.ProductEntity
import com.vaasugambhir.furniturekart.data.room.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {
    fun getAllProducts() = productRepository.getAllProducts()

    fun insert(product: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) { productRepository.insert(product) }
    }

    fun delete(product: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) { productRepository.delete(product) }
    }
}