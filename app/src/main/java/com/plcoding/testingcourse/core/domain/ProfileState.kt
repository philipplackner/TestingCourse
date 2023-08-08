package com.plcoding.testingcourse.core.domain

import com.plcoding.testingcourse.core.domain.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
