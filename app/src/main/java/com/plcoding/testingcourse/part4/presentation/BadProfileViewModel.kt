package com.plcoding.testingcourse.part4.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.plcoding.testingcourse.R

class BadProfileViewModel(
//    private val app: Application
//) : AndroidViewModel(app) {
) : ViewModel() {

    private val analytics = Firebase.analytics

    var state by mutableStateOf(BadProfileState())
        private set

    fun saveProfile() {
        analytics.logEvent("save_profile") {
            param("profile_id", state.profileId)
            param("username", state.username)
        }
        state = state.copy(
            infoMessage = app.getString(R.string.successfully_saved_profile)
        )
    }
}
