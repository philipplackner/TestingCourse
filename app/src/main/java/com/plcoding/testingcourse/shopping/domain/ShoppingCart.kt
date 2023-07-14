package com.plcoding.testingcourse.shopping.domain

import com.plcoding.testingcourseexamples.part1.domain.ShoppingCartCache


class ShoppingCart(
    private val cache: ShoppingCartCache
) {

    private val items = cache.loadCart().toMutableList()

    fun addProduct(product: Product, quantity: Int) {
        if(quantity < 0) {
            throw IllegalArgumentException("Quantity can't be negative")
        }
        repeat(quantity) {
            items.add(product)
        }
        cache.saveCart(items)
    }

    fun getTotalCost(): Double {
        return items.sumOf { it.price }
    }
}