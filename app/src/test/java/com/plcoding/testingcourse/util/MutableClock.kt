package com.plcoding.testingcourse.util

import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

class MutableClock(private val delegate: Clock): Clock() {
    private var offset: Duration = Duration.ZERO

    fun advanceTimeBy(duration: Duration) {
        offset = offset.plus(duration)
    }

    override fun getZone(): ZoneId {
        return delegate.zone
    }

    override fun withZone(zoneId: ZoneId?): Clock {
        return MutableClock(delegate.withZone(zoneId)).also {
            it.offset = offset
        }
    }

    override fun instant(): Instant {
        return delegate.instant().plus(offset)
    }
}