package com.amir.springboot.controller

import com.amir.springboot.dto.ProductDTO
import com.amir.springboot.service.SportingGoodsService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sporting-goods")
class SportingGoodsController(private val service: SportingGoodsService) {

    @GetMapping
    fun getAllSportingGoods(): List<ProductDTO> = service.getAllGoods()

    @GetMapping("/{id}")
    fun getSportingGoodById(@PathVariable id: Long): ProductDTO = service.getGoodById(id)

    @PostMapping
    fun addSportingGood(@RequestBody productDTO: ProductDTO): Boolean = service.addGood(productDTO)

    @PutMapping("/{id}")
    fun updateSportingGood(
        @PathVariable id: Long,
        @RequestParam count: Int
    ): Boolean = service.updateGood(id, count)

    @DeleteMapping("/{id}")
    fun deleteSportingGood(@PathVariable id: Long): Boolean = service.deleteGood(id)
}