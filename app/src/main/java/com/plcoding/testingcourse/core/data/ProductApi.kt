package com.plcoding.testingcourse.core.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {

    @POST("/purchase")
    suspend fun purchaseProducts(
        @Body products: ProductsDto
    )

    @POST("/cancelPurchase/{id}")
    suspend fun cancelPurchase(
        @Path("id") purchaseId: String
    )
}