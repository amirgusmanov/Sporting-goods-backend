package com.amir.springboot.model

import jakarta.persistence.*

@Entity
open class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,
    open var name: String,
    open var price: Double,
    open var description: String,
    open var productQuantity: Int = 1,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products")
    open val user: User
)