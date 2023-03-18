package com.dev.bayan_ibrahim.bus_scheduler.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.bayan_ibrahim.bus_scheduler.data.local.dao.BusStopDao
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel

const val DATABASE_NAME = "stop_list_db"

@Database(entities = [BusStopTravel::class], version = 1, exportSchema = false)
abstract class BusStopDatabase: RoomDatabase() {
    abstract fun dao(): BusStopDao

    companion object {
        @Volatile
        private var Instance: BusStopDatabase? = null

        fun getDatabase(context: Context): BusStopDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BusStopDatabase::class.java, DATABASE_NAME)
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}