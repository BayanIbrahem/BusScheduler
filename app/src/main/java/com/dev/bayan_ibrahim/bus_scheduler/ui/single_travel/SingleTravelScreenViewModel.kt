package com.dev.bayan_ibrahim.bus_scheduler.ui.single_travel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.SingleTravelUiState
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel
import com.dev.bayan_ibrahim.bus_scheduler.domain.repo.BusStopDatabaseRepo
import com.dev.bayan_ibrahim.bus_scheduler.ui.SingleTravelDestination
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val TIMEOUT_MILLIS = 5_000L
class SingleTravelScreenViewModel(val repo: BusStopDatabaseRepo, private val savedStateHandle: SavedStateHandle): ViewModel() {
    val singleTravelId: Int = savedStateHandle[SingleTravelDestination.travelIdArg] ?: -1
    val singleTravelName: String? = savedStateHandle[SingleTravelDestination.travelNameArg]

    /**
     * there is three states:
     * 1- this screen showed after pressing new in home screen.
     *      this state will make the [singleTravelId] equals -1
     *      and [singleTravelName] is null this will lead to else
     *      block and create new default instance from uiState.
     *      (add new travel)
     * 2- this screen showed after pressing edit screen contains
     *      details of a bus stop this state will make the
     *      [singleTravelId] equals -1 and [singleTravelName]
     *      is null this will lead to else if block and add name
     *      read only for it, this item will be inserted to database.
     *      (add new travel for this bus station)
     * 3- finally when we press edit from single travel screen,
     *      both values will be valid and first block will be executed
     *      (edit existed travel)

     * */
    private val _uiState: MutableStateFlow<SingleTravelUiState> = MutableStateFlow(
        SingleTravelUiState()
    )
    val uiState: StateFlow<SingleTravelUiState> = _uiState

    init{
        viewModelScope.launch {
            _uiState.value = repo.getTravel(singleTravelId)
                .map {
                    if (it != null ) {
                        SingleTravelUiState(travel = it, isNew = false)
                    } else if (singleTravelName != null)  {
                        SingleTravelUiState(
                            travel = BusStopTravel(
                                name = singleTravelName,
                                arrivalTime = "",
                            ),
                            isNew = false,
                        )
                    }
                    else {
                        SingleTravelUiState()
                    }
                }.first()
        }
    }

    fun saveOrUpdate(): Boolean {
        val isValid = checkValidData()
        viewModelScope.launch {
            if (uiState.value.travel.id == null) {
                if (isValid) {
                    repo.insert(uiState.value.travel)
                }
            } else {
                if (isValid) {
                    repo.update(uiState.value.travel)
                }
            }
        }
        return isValid
    }
    private fun checkValidData(): Boolean {
        return uiState.value.travel.name.isNotBlank() && uiState.value.travel.arrivalTime.isNotBlank()
    }

    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(travel = it.travel.copy(name = name))
        }
    }
    fun onArrivalTimeChanged(arrivalTime: String) {
        _uiState.update {
            it.copy(travel = it.travel.copy(arrivalTime = arrivalTime))
        }
    }

    fun delete() {
        viewModelScope.launch {
            repo.delete(uiState.value.travel)
        }
    }

}