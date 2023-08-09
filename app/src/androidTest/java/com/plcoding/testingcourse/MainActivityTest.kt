package com.plcoding.testingcourse

import android.content.ComponentName
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsRule
import com.plcoding.testingcourse.part12.presentation.ProfileActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

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


}