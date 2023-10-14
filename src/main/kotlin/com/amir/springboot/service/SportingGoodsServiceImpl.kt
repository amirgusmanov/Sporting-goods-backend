package com.amir.springboot.service

import com.amir.springboot.dto.ProductDTO
import com.amir.springboot.model.Product
import com.amir.springboot.repository.SportingGoodsRepository
import com.amir.springboot.util.Constants
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class SportingGoodsServiceImpl(private val repository: SportingGoodsRepository) : SportingGoodsService {

    override fun getAllGoods(): List<ProductDTO> = repository.findAll()
        .map { product ->
            ProductDTO(
                id = product.id,
                name = product.name,
                price = product.price,
                description = product.description,
                productQuantity = product.productQuantity
            )
        }

    override fun addGood(good: ProductDTO): Boolean = try {
        repository.save(
            Product(
                name = good.name,
                price = good.price,
                description = good.description,
                productQuantity = good.productQuantity
            )
        )
        true
    } catch (e: Exception) {
        false
    }

    override fun getGoodById(id: Long): ProductDTO {
        val good = repository
            .findById(id)
            .orElseThrow { NoSuchElementException(Constants.NO_SUCH_ELEMENT_BY_ID) }

        return ProductDTO(
            id = good.id,
            name = good.name,
            price = good.price,
            description = good.description,
            productQuantity = good.productQuantity
        )
    }

    override fun updateGood(id: Long, count: Int): Boolean {
        val existingSportsGood = repository
            .findById(id)
            .orElseThrow { NoSuchElementException(Constants.NO_SUCH_ELEMENT_BY_ID) }

        existingSportsGood.productQuantity = count
        return try {
            repository.save(existingSportsGood)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun deleteGood(id: Long): Boolean {
        repository.deleteById(id)
        return true
    }
}