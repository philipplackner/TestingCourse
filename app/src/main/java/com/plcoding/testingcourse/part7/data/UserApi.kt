package com.plcoding.testingcourse.part7.data

import com.plcoding.testingcourse.part7.domain.Post
import com.plcoding.testingcourse.part7.domain.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/user")
    suspend fun getUser(@Path("id") id: String): User?

    @GET("/posts")
    suspend fun getPosts(@Query("userId") id: String): List<Post>
}