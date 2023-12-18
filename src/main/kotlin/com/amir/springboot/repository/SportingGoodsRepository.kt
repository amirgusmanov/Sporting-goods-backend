package com.amir.springboot.repository

import com.amir.springboot.model.Product
import com.amir.springboot.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface SportingGoodsRepository : JpaRepository<Product, Long> {
    fun findByUser(user: User): List<Product>
}