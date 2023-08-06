package com.plcoding.testingcourse.part7.presentation

import com.plcoding.testingcourse.part7.domain.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
