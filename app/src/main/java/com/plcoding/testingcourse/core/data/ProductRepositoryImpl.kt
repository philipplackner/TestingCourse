package com.plcoding.testingcourse.core.data

import com.plcoding.testingcourse.core.domain.Product
import com.plcoding.testingcourse.core.domain.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CancellationException

class ProductRepositoryImpl(
    private val productApi: ProductApi,
): ProductRepository {

    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {
        return try {
            productApi.purchaseProducts(
                products = ProductsDto(products)
            )
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch(e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            Result.failure(e)
        }
    }

    override suspend fun cancelPurchase(purchaseId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}