package com.amir.springboot.dto

data class ProductDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String,
    val productQuantity: Int
)