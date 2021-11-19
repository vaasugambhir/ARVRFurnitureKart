package com.vaasugambhir.furniturekart.ui.interfaces

import com.vaasugambhir.furniturekart.data.room.ProductEntity

interface OnProductClickListener {
    fun onProductClick(product: ProductEntity)

    fun onDeleteClick(product: ProductEntity)

    fun onOrderClick(product: ProductEntity)
}