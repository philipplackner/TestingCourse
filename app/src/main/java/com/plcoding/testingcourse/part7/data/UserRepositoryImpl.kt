package com.plcoding.testingcourse.part7.data

import com.plcoding.testingcourse.core.domain.Profile
import com.plcoding.testingcourse.part7.domain.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl(
    private val api: UserApi
): UserRepository {

    override suspend fun getProfile(userId: String): Result<Profile> {
        return coroutineScope {
            val userResult = async {
                try {
                    Result.success(api.getUser(userId))
                } catch(e: HttpException) {
                    Result.failure(e)
                } catch(e: IOException) {
                    Result.failure(e)
                }
            }
            val postsResult = async {
                try {
                    Result.success(api.getPosts(userId))
                } catch(e: HttpException) {
                    Result.failure(e)
                } catch(e: IOException) {
                    Result.failure(e)
                }
            }

            val fetchedUser = userResult.await().getOrNull()
            if(fetchedUser != null) {
                Result.success(
                    Profile(
                        user = fetchedUser,
                        posts = postsResult.await().getOrNull() ?: emptyList()
                    )
                )
            } else {
                Result.failure(
                    userResult.await().exceptionOrNull() ?: Exception("Unknown error")
                )
            }

        }
    }
}