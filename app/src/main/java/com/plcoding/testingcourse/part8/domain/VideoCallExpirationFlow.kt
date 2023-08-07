package com.plcoding.testingcourse.part8.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.Clock

class VideoCallExpirationFlow(
    private val calls: List<ScheduledVideoCall>,
    private val clock: Clock = Clock.systemDefaultZone()
): Flow<List<ScheduledVideoCall>> {

    private fun tickerFlow() = flow {
        while (true) {
            emit(Unit)
            delay(1000L)
        }
    }

    override suspend fun collect(collector: FlowCollector<List<ScheduledVideoCall>>) {
        tickerFlow()
            .flowOn(Dispatchers.Main)
            .map {
                calls.filter { it.isExpired(clock) }
            }
            .distinctUntilChanged()
            .collect(collector)
    }
}