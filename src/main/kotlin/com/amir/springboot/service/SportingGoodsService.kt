package com.amir.springboot.service

import com.amir.springboot.dto.ProductDTO

interface SportingGoodsService {
    fun getAllGoods(): List<ProductDTO>
    fun addGood(good: ProductDTO): Boolean
    fun getGoodById(id: Long): ProductDTO
    fun updateGood(id: Long, count: Int): Boolean
    fun deleteGood(id: Long): Boolean
}