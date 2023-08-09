package com.plcoding.testingcourse.part12.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.plcoding.testingcourse.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CountdownNotification(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(
        Context.NOTIFICATION_SERVICE
    ) as NotificationManager

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

    private var countdownJob: Job? = null

    private fun countdownFlow(startSeconds: Int): Flow<Int> {
        return flow<Int> {
            var currentSeconds = startSeconds
            while(currentSeconds > 0) {
                emit(currentSeconds--)
                delay(1000L)
            }
        }
    }

    fun startCountdown(seconds: Int) {
        countdownJob?.cancel()
        countdownJob = countdownFlow(seconds).onEach {
            showOrUpdateNotification(it)
        }.launchIn(MainScope())
    }

    private fun showOrUpdateNotification(time: Int) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentText("Counting down: $time")
            .setContentTitle("Countdown")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(1, notification)
    }

    companion object {
        const val CHANNEL_ID = "countdown"
        const val CHANNEL_NAME = "Countdown"
    }
}