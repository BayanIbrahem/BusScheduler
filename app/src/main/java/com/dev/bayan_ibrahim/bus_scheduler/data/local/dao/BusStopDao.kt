package com.dev.bayan_ibrahim.bus_scheduler.data.local.dao

import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BusStopDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stop: BusStopTravel)

    @Update()
    suspend fun update(stop: BusStopTravel)

    @Delete
    suspend fun delete(stop: BusStopTravel)

    @Query("DELETE FROM stop_list where stop_name = :name")
    fun deleteAll(name: String)


    @Query("SELECT * FROM stop_list")
    fun getAll(): Flow<List<BusStopTravel>>

    @Query("SELECT * FROM stop_list WHERE stop_name = :name")
    fun getAllWithName(name: String): Flow<List<BusStopTravel>>

    @Query("SELECT * FROM stop_list WHERE id = :id")
    fun getTravel(id: Int): Flow<BusStopTravel?>

}
