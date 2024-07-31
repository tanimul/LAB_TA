package com.example.lab_ta.presentations.fragments.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_ta.presentations.fragments.profile.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private val _uiAction = Channel<DetailsUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    fun uiActions(actions: DetailsUiActions) {
        _uiAction.trySend(actions)
    }

    private val _userInfo = Channel<String>(Channel.CONFLATED)
    val userInfo = _userInfo.receiveAsFlow().flatMapLatest {
        profileUseCase(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)


}

sealed interface DetailsUiActions {
    data object NavigateBack : DetailsUiActions
}