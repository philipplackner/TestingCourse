package com.plcoding.testingcourse.part11.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.plcoding.testingcourse.MainActivity
import com.plcoding.testingcourse.ui.theme.TestingCourseTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testProfileScreenUi_profileLoaded() {
        composeRule.setContent {
            TestingCourseTheme {
                ProfileScreen(
                    state = previewProfileState()
                )
            }
        }

        composeRule.onNodeWithText("Test username").assertIsDisplayed()
        composeRule.onNodeWithText("Title1").assertIsDisplayed()
        composeRule.onNodeWithText("Body1").assertIsDisplayed()
    }
}