package com.plcoding.testingcourse.part4.presentation

import android.app.Application
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BadProfileViewModelTest {

    private lateinit var viewModel: BadProfileViewModel

    @BeforeEach
    fun setUp() {
        viewModel = BadProfileViewModel(Application()) // <-- This is the problem, Application is passed in just to make the test pass
    }

    @Test
    fun `Test saving profile`() {

    }
}
