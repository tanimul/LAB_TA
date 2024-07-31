package com.example.lab_ta.presentations.fragments.splash.presentation

import android.os.Handler
import android.os.Looper
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
import javax.inject.Inject

class SplashViewModel : ViewModel() {

    init {
        timer()
    }

    private val _uiAction = Channel<SplashUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    fun uiActions(actions: SplashUiActions) {
        _uiAction.trySend(actions)
    }

    private fun timer() {
        Handler(Looper.getMainLooper()).postDelayed({
            _uiAction.trySend(SplashUiActions.OnNavigate)
        }, 1000)
    }

}

sealed interface SplashUiActions {
    data object NavigateBack : SplashUiActions
    data object OnNavigate : SplashUiActions
}