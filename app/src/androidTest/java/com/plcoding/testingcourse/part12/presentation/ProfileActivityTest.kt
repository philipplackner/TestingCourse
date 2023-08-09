package com.plcoding.testingcourse.part12.presentation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performScrollToIndex
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ProfileActivity>()

    private lateinit var preferences: SharedPreferences

    @Before
    fun setUp() {
        preferences = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @After
    fun tearDown() {
        preferences.edit().clear().commit()
    }

    @Test
    fun testSaveScrollPosition() {
        val scenario = composeRule.activityRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)

        composeRule.onNode(hasScrollAction()).performScrollToIndex(50)

        scenario.moveToState(Lifecycle.State.DESTROYED)

        val scrollPosition = preferences.getInt("scroll_position", -1)
        assertThat(scrollPosition).isEqualTo(50)
    }
}