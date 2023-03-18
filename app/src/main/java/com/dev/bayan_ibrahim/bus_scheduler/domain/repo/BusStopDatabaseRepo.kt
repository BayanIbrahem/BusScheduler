package com.dev.bayan_ibrahim.bus_scheduler.domain.repo

import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel
import kotlinx.coroutines.flow.Flow

interface BusStopDatabaseRepo {
    suspend fun insert(stop: BusStopTravel)

    suspend fun update(stop: BusStopTravel)

    suspend fun delete(stop: BusStopTravel)

    suspend fun deleteAll(name: String)

    fun getAll(): Flow<List<BusStopTravel>>

    fun getAllWithName(name: String): Flow<List<BusStopTravel>>

    fun getTravel(id: Int): Flow<BusStopTravel?>
}