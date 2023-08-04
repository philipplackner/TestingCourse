package com.plcoding.testingcourseexamples.part5.data

import com.plcoding.testingcourse.core.data.ProductsDto
import com.plcoding.testingcourseexamples.part1.domain.Product
import com.plcoding.testingcourseexamples.part4.domain.AnalyticsLogger
import com.plcoding.testingcourseexamples.part4.domain.LogParam
import com.plcoding.testingcourseexamples.part5.domain.ProductRepository
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CancellationException

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val analyticsLogger: AnalyticsLogger
): ProductRepository {

    override suspend fun purchaseProducts(products: List<Product>): Result<Unit> {
        return try {
            val obj = Product(
                id = 1,
                name = "Not mocked product",
                price = 5.0
            )
//            println(obj.name)
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

    override suspend fun cancelPurchase(purchaseId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}