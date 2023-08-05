package com.plcoding.testingcourse.part4.presentation

import com.plcoding.testingcourse.core.domain.AnalyticsLogger
import com.plcoding.testingcourse.core.domain.LogParam
import org.junit.jupiter.api.BeforeEach

internal class GoodProfileViewModelTest {

    private lateinit var viewModel: GoodProfileViewModel

    @BeforeEach
    fun setUp() {
        viewModel = GoodProfileViewModel(
            analytics = object : AnalyticsLogger {
                override fun logEvent(key: String, vararg params: LogParam<Any>) = Unit
            }
        )
    }
}