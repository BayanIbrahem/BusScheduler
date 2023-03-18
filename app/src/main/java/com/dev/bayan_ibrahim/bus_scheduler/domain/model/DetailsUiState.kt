package com.dev.bayan_ibrahim.bus_scheduler.domain.model

import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel

data class DetailsUiState(
    val travelsList: List<BusStopTravel> = listOf(),
    val name: String = travelsList.firstOrNull()?.name ?: "",
)
