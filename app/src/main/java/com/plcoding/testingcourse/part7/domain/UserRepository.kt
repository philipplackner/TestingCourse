package com.plcoding.testingcourse.part7.domain

import com.plcoding.testingcourse.part7.domain.Profile

interface UserRepository {
    suspend fun getProfile(userId: String): Result<Profile>
}