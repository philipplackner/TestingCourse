package com.plcoding.testingcourseexamples.part1.domain

import com.plcoding.testingcourse.part1.domain.Product

interface ShoppingCartCache {
    fun saveCart(items: List<Product>)
    fun loadCart(): List<Product>
    fun clearCart()
}