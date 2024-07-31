package com.example.lab_ta.presentations.fragments.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.presentations.fragments.registration.domain.usecase.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val _uiAction = Channel<RegistrationUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    fun uiActions(actions: RegistrationUiActions) {
        _uiAction.trySend(actions)
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationUseCase(user)
        }
    }

}

sealed interface RegistrationUiActions {
    data object NavigateBack : RegistrationUiActions
    data object OnRegistration : RegistrationUiActions
}