@file:OptIn(ExperimentalCoroutinesApi::class)

package com.plcoding.testingcourse.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.time.Duration
import kotlin.time.toJavaDuration

fun TestScope.advanceTimeBy(duration: Duration, clock: MutableClock) {
    advanceTimeBy(duration)
    clock.advanceTimeBy(duration.toJavaDuration())
}