package com.plcoding.testingcourse.part7.data

import com.plcoding.testingcourse.core.domain.Post
import com.plcoding.testingcourse.core.domain.User

class UserApiFake: UserApi {

    var users = (1..10).map {
        User(
            id = it.toString(),
            username = "User$it"
        )
    }

    var posts = (1..10).map {
        Post(
            id = it.toString(),
            userId = it.toString(),
            title = "Test title$it",
            body = "Test body$it"
        )
    }

    override suspend fun getUser(id: String): User? {
        return users.find { it.id == id }
    }

    override suspend fun getPosts(id: String): List<Post> {
        return posts.filter { it.userId == id }
    }
}