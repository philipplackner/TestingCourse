package com.plcoding.testingcourseexamples.part1.domain

interface ShoppingCartCache {
    fun saveCart(items: List<Product>)
    fun loadCart(): List<Product>
    fun clearCart()
}