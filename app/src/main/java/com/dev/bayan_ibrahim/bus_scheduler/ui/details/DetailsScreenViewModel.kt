package com.dev.bayan_ibrahim.bus_scheduler.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.DetailsUiState
import com.dev.bayan_ibrahim.bus_scheduler.domain.repo.BusStopDatabaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val TIMEOUT_MILLIS = 5_000L
class DetailsScreenViewModel(val repo: BusStopDatabaseRepo, private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val travelName: String = checkNotNull(savedStateHandle[DetailsScreenDestination.itemNameArg])

    val detailsUiState: StateFlow<DetailsUiState> =
        repo.getAllWithName(travelName)
            .filterNotNull()
            .map {
                DetailsUiState(travelsList = it, name = travelName)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailsUiState()
            )

    fun delete() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.deleteAll(detailsUiState.value.name)
            }
        }
    }

}