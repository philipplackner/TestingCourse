package com.plcoding.testingcourse.part7.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.testingcourse.core.domain.ProfileState
import com.plcoding.testingcourse.part7.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val userId = savedStateHandle.get<String>("userId")

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {
            userId?.let { id ->
                _state.update { it.copy(isLoading = true) }

                val result = repository.getProfile(id)

                _state.update { it.copy(
                    profile = result.getOrNull(),
                    errorMessage = result.exceptionOrNull()?.message,
                    isLoading = false
                ) }
            }
        }
    }
}
