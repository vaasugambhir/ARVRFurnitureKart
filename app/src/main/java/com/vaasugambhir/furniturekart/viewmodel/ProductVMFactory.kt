package com.vaasugambhir.furniturekart.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaasugambhir.furniturekart.data.room.ProductRepository
import java.lang.IllegalArgumentException

class ProductVMFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(ProductRepository(application)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}