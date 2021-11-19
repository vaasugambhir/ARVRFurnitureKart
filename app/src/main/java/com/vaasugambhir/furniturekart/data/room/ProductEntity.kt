package com.vaasugambhir.furniturekart.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val key: String,
    val imageRaw: Int,
    val arRaw: Int,
    val name: String,
)
