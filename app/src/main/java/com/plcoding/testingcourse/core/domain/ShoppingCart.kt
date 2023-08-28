package com.plcoding.testingcourse.core.domain


class ShoppingCart(
    private val cache: ShoppingCartCache
) {

    private val validProductIds = listOf(0, 1, 2, 3, 4, 5)
    private val items = cache.loadCart().toMutableList()

    fun addProduct(product: Product, quantity: Int) {
        if(quantity < 0) {
            throw IllegalArgumentException("Quantity can't be negative")
        }
        if(isValidProduct(product)) {
            repeat(quantity) {
                items.add(product)
            }
            cache.saveCart(items)
        }
    }

    private fun isValidProduct(product: Product): Boolean {
        return product.price >= 0.0 && product.id in validProductIds
    }

    fun getTotalCost(): Double {
        return items.sumOf { it.price }
    }
}
