package com.example.lab_ta.presentations.fragments.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_ta.network.core.Resource
import com.example.lab_ta.presentations.fragments.dashboard.domain.model.Content
import com.example.lab_ta.presentations.fragments.dashboard.domain.model.Contents
import com.example.lab_ta.presentations.fragments.dashboard.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository,
) : ViewModel() {

    private val _uiAction = Channel<DashboardUiActions>(Channel.CONFLATED)
    val uiAction = _uiAction.receiveAsFlow()

    private val _items = MutableStateFlow<List<Contents>>(emptyList())
    val items: StateFlow<List<Contents>> get() = _items

    fun updateItems(newItems: List<Contents>) {
        _items.value = newItems
    }

    fun uiActions(actions: DashboardUiActions) {
        _uiAction.trySend(actions)
    }
    fun getContent(
        query: String
    ): Flow<Resource<List<Contents>>> {
        Timber.d("getContents:")
        return repository.getContent(query).flowOn(Dispatchers.IO)
    }


}

sealed interface DashboardUiActions {
    data object NavigateBack : DashboardUiActions
    data object OnProfile : DashboardUiActions
}