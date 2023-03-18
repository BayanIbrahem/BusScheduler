package com.dev.bayan_ibrahim.bus_scheduler.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.HomeUiState
import com.dev.bayan_ibrahim.bus_scheduler.domain.repo.BusStopDatabaseRepo
import kotlinx.coroutines.flow.*

const val TIMEOUT_MILLIS = 5000L
class HomeScreenViewModel(repo: BusStopDatabaseRepo): ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        repo.getAll()
            .filterNotNull()
            .map {
                HomeUiState(stopList = it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}