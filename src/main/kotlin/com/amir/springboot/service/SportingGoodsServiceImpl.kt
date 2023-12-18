package com.amir.springboot.service

import com.amir.springboot.dto.ProductDTO
import com.amir.springboot.model.Product
import com.amir.springboot.repository.SportingGoodsRepository
import com.amir.springboot.repository.UserRepository
import com.amir.springboot.util.Constants
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class SportingGoodsServiceImpl(
    private val productRepository: SportingGoodsRepository,
    private val userRepository: UserRepository
) : SportingGoodsService {

    override fun getAllGoods(email: String): List<ProductDTO> {
        val user = userRepository.findByEmail(email) ?: throw NoSuchElementException(Constants.USER_NOT_FOUND_MSG)
        return productRepository.findByUser(user)
            .map { product ->
                ProductDTO(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    description = product.description,
                    productQuantity = product.productQuantity
                )
            }
    }

    override fun addGood(good: ProductDTO, email: String): Boolean = try {
        val user = userRepository.findByEmail(email) ?: throw NoSuchElementException(Constants.USER_NOT_FOUND_MSG)
        productRepository.save(
            Product(
                name = good.name,
                price = good.price,
                description = good.description,
                productQuantity = good.productQuantity,
                user = user
            )
        )
        true
    } catch (e: Exception) {
        false
    }

    override fun getGoodById(id: Long): ProductDTO {
        val good = productRepository
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

    @Throws(AccessDeniedException::class)
    override fun updateGood(id: Long, count: Int, email: String): Boolean {
        val user = userRepository.findByEmail(email) ?: throw NoSuchElementException(Constants.USER_NOT_FOUND_MSG)
        val existingSportsGood = productRepository
            .findById(id)
            .orElseThrow { NoSuchElementException(Constants.NO_SUCH_ELEMENT_BY_ID) }

        if (existingSportsGood.user != user) {
            throw java.nio.file.AccessDeniedException(Constants.DENIED_ACCESS)
        }

        existingSportsGood.productQuantity = count
        return try {
            productRepository.save(existingSportsGood)
            true
        } catch (e: Exception) {
            false
        }
    }

    @Throws(AccessDeniedException::class)
    override fun deleteGood(id: Long, email: String): Boolean {
        val user = userRepository.findByEmail(email) ?: throw NoSuchElementException(Constants.USER_NOT_FOUND_MSG)
        val product = productRepository
            .findById(id)
            .orElseThrow { NoSuchElementException(Constants.NO_SUCH_ELEMENT_BY_ID) }

        if (product.user != user) {
            throw java.nio.file.AccessDeniedException(Constants.DENIED_ACCESS)
        }

        productRepository.deleteById(id)
        return true
    }
}