package com.plcoding.testingcourse.part7.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private lateinit var api: UserApiFake

    @BeforeEach
    fun setUp() {
        api = UserApiFake()
        repository = UserRepositoryImpl(api)
    }

    @Test
    fun `Test getting profile`() = runBlocking {
        val profileResult = repository.getProfile("1")

        assertThat(profileResult.isSuccess).isTrue()
        assertThat(profileResult.getOrThrow().user.id).isEqualTo("1")

        val expectedPosts = api.posts.filter { it.userId == "1" }
        assertThat(profileResult.getOrThrow().posts).isEqualTo(expectedPosts)
    }
}