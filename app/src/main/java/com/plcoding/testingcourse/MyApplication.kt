package com.plcoding.testingcourse

import android.app.Application
import com.plcoding.testingcourse.part12.presentation.CountdownNotification

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CountdownNotification(this).createNotificationChannel()
    }
}