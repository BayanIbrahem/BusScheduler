package com.dev.bayan_ibrahim.bus_scheduler.domain.model

import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel

data class HomeUiState(
    val stopList: List<BusStopTravel> = listOf()
)
