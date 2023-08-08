package com.plcoding.testingcourse.part7.data

import com.plcoding.testingcourse.core.domain.Profile
import com.plcoding.testingcourse.part7.domain.UserRepository
import com.plcoding.testingcourse.util.profile

class UserRepositoryFake: UserRepository {

    var profileToReturn = profile()
    var errorToReturn: Exception? = null

    override suspend fun getProfile(userId: String): Result<Profile> {
        return if(errorToReturn != null) {
            Result.failure(errorToReturn!!)
        } else Result.success(profileToReturn)
    }
}