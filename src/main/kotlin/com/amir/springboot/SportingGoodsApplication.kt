package com.amir.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.amir.springboot", "com.amir.springboot.controller", "com.amir.springboot.service"])
class SportingGoodsApplication

fun main(args: Array<String>) { runApplication<SportingGoodsApplication>(*args) }