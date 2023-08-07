package com.plcoding.testingcourse.part8.domain

import java.time.Clock
import java.time.LocalDateTime

data class ScheduledVideoCall(
    val title: String,
    val remoteUserId: String,
    val time: LocalDateTime,
) {
    fun isExpired(): Boolean {
        return time.isBefore(LocalDateTime.now())
    }
}
