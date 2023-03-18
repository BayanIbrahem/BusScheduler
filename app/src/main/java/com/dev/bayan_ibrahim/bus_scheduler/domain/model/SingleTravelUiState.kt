package com.dev.bayan_ibrahim.bus_scheduler.domain.model

import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel

data class SingleTravelUiState(
    val travel: BusStopTravel = BusStopTravel(name = "", arrivalTime = ""),
    val isNew: Boolean = true, // either it is new so we can add name or it is already existed so we can't change it.
)
