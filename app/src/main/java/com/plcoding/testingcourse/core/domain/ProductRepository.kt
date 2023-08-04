package com.plcoding.testingcourse.core.domain

interface ProductRepository {
    suspend fun purchaseProducts(products: List<Product>): Result<Unit>
    suspend fun cancelPurchase(purchaseId: Int): Result<Unit>
}