package com.dev.bayan_ibrahim.bus_scheduler.data.repo

import com.dev.bayan_ibrahim.bus_scheduler.data.local.dao.BusStopDao
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel
import com.dev.bayan_ibrahim.bus_scheduler.domain.repo.BusStopDatabaseRepo
import kotlinx.coroutines.flow.Flow

class BusStopDatabaseRepoImpl(val dao: BusStopDao): BusStopDatabaseRepo {
    override suspend fun insert(stop: BusStopTravel) = dao.insert(stop)

    override suspend fun update(stop: BusStopTravel) = dao.update(stop)

    override suspend fun delete(stop: BusStopTravel) = dao.delete(stop)

    override suspend fun deleteAll(name: String) = dao.deleteAll(name)

    override fun getAll(): Flow<List<BusStopTravel>> = dao.getAll()

    override fun getAllWithName(name: String): Flow<List<BusStopTravel>> = dao.getAllWithName(name)

    override fun getTravel(id: Int): Flow<BusStopTravel?> = dao.getTravel(id)
}