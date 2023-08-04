package com.plcoding.testingcourse.core.data

import com.plcoding.testingcourse.core.domain.Product
import com.plcoding.testingcourse.core.domain.ShoppingCartCache

class ShoppingCartCacheFake: ShoppingCartCache {

    private var items = emptyList<Product>()

    override fun saveCart(items: List<Product>) {
        this.items = items
    }

    override fun loadCart(): List<Product> {
        return items
    }

    override fun clearCart() {
        items = emptyList()
    }
}