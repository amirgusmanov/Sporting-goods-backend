package com.amir.springboot.repository

import com.amir.springboot.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface SportingGoodsRepository : JpaRepository<Product, Long>