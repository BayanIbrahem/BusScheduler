package com.dev.bayan_ibrahim.bus_scheduler

import android.app.Application
import com.dev.bayan_ibrahim.bus_scheduler.di.AppContainer
import com.dev.bayan_ibrahim.bus_scheduler.di.DefaultAppContainer

class App: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
