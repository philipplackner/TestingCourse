package com.plcoding.testingcourse.core.domain

data class Profile(
    val user: User,
    val posts: List<Post>
)
