package com.dev.bayan_ibrahim.bus_scheduler.di

import android.content.Context
import com.dev.bayan_ibrahim.bus_scheduler.App
import com.dev.bayan_ibrahim.bus_scheduler.data.local.db.BusStopDatabase
import com.dev.bayan_ibrahim.bus_scheduler.data.repo.BusStopDatabaseRepoImpl
import com.dev.bayan_ibrahim.bus_scheduler.domain.repo.BusStopDatabaseRepo

interface AppContainer {
    val repo: BusStopDatabaseRepo
}

class DefaultAppContainer(private val context: Context): AppContainer {
    override val repo: BusStopDatabaseRepo =
        BusStopDatabaseRepoImpl(
            dao = BusStopDatabase
                .getDatabase(
                    context = context
                ).dao()
        )

}