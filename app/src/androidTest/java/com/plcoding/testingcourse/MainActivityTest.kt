package com.plcoding.testingcourse

import android.Manifest
import android.content.ComponentName
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.plcoding.testingcourse.part12.presentation.ProfileActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

//    @get:Rule
//    val grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.RECORD_AUDIO)

//    @get:Rule
//    val intentsRule = IntentsRule()
//
//    @Test
//    fun testLaunchingOtherActivity() {
//        composeRule.onNodeWithText("Send intent").performClick()
//
//        Intents.intended(
//            IntentMatchers.hasComponent(
//                ComponentName(composeRule.activity.applicationContext, ProfileActivity::class.java)
//            )
//        )
//        Intents.intended(IntentMatchers.hasExtra("TEST_EXTRA", "top secret"))
//        Intents.intended(IntentMatchers.hasAction("MY_ACTION"))
//    }

    @Test
    fun testRecordAudioPermissionDenial_showsErrorDialog() {
        composeRule.onNodeWithText("Record").performClick()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val dontAllowButton = device.findObject(UiSelector().textStartsWith("Don"))
        dontAllowButton.click()

        composeRule.onNodeWithText("Record").performClick()
        dontAllowButton.click()

        composeRule.onNodeWithText("Can't record without permission").assertIsDisplayed()
    }
}