package com.example.lab_ta.presentations.fragments.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_ta.presentations.fragments.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiAction = Channel<LoginUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    fun uiActions(actions: LoginUiActions) {
        _uiAction.trySend(actions)
    }

    private val _userInfo = Channel<String>(Channel.CONFLATED)
    val userInfo = _userInfo.receiveAsFlow().flatMapLatest {
        loginUseCase(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            _userInfo.trySend(userId)
        }
    }

}

sealed interface LoginUiActions {
    data object NavigateBack : LoginUiActions
    data object OnLogin : LoginUiActions
    data object OnRegistration : LoginUiActions
}