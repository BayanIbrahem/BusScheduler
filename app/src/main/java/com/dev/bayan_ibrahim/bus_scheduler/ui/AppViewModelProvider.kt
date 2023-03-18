package com.dev.bayan_ibrahim.bus_scheduler.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dev.bayan_ibrahim.bus_scheduler.App
import com.dev.bayan_ibrahim.bus_scheduler.ui.details.DetailsScreenViewModel
import com.dev.bayan_ibrahim.bus_scheduler.ui.home.HomeScreenViewModel
import com.dev.bayan_ibrahim.bus_scheduler.ui.single_travel.SingleTravelScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeScreenViewModel(
                repo = busStopApplication().container.repo
            )
        }
        initializer {
            DetailsScreenViewModel(
                repo = busStopApplication().container.repo,
                savedStateHandle = this.createSavedStateHandle()
            )
        }
        initializer {
            SingleTravelScreenViewModel(
                repo = busStopApplication().container.repo,
                savedStateHandle = this.createSavedStateHandle()
            )
        }
    }
}
/**
 * Extension function to queries for [Application] object and returns an instance of
 * [App].
 */
fun CreationExtras.busStopApplication(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)