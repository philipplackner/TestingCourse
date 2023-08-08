package com.plcoding.testingcourse.util

import com.plcoding.testingcourse.core.domain.Post
import com.plcoding.testingcourse.core.domain.Profile
import com.plcoding.testingcourse.core.domain.User
import com.plcoding.testingcourse.part8.domain.ScheduledVideoCall
import java.time.LocalDateTime
import java.util.UUID

fun user(): User {
    return User(
        id = UUID.randomUUID().toString(),
        username = "test-user"
    )
}

fun post(userId: String): Post {
    return Post(
        id = UUID.randomUUID().toString(),
        userId = userId,
        title = "test title",
        body = "test body"
    )
}

fun profile(): Profile {
    val user = user()
    return Profile(
        user = user,
        posts = (1..10).map {
            post(user.id)
        }
    )
}

fun scheduledVideoCall(
    time: LocalDateTime
): ScheduledVideoCall {
    return ScheduledVideoCall(
        title = UUID.randomUUID().toString(),
        remoteUserId = UUID.randomUUID().toString(),
        time = time
    )
}