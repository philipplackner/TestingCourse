package com.plcoding.testingcourse.core.data

import com.plcoding.testingcourse.core.domain.Product
import com.plcoding.testingcourse.core.domain.ProductRepository
import com.plcoding.testingcourse.core.domain.AnalyticsLogger
import com.plcoding.testingcourse.core.domain.LogParam
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CancellationException

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val analyticsLogger: AnalyticsLogger
): ProductRepository {

    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {
        return try {
            productApi.purchaseProducts(
                products = ProductsDto(products)
            )
            Result.success(Unit)
        } catch (e: HttpException) {
            analyticsLogger.logEvent(
                "http_error",
                LogParam("code", e.code()),
                LogParam("message", e.message()),
            )
            Result.failure(e)
        } catch(e: IOException) {
            analyticsLogger.logEvent(
                "io_error",
                LogParam("message", e.message.toString())
            )
            Result.failure(e)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            Result.failure(e)
        }
    }

    override suspend fun cancelPurchase(purchaseId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }
}