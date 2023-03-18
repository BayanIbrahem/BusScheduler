package com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stop_list")
data class BusStopTravel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "stop_name")
    val name: String,
    @ColumnInfo(name = "stop_arrival")
    val arrivalTime: String,
)