package com.dev.bayan_ibrahim.bus_scheduler.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel
import com.dev.bayan_ibrahim.bus_scheduler.ui.AppViewModelProvider
import com.dev.bayan_ibrahim.bus_scheduler.ui.navigation.NavigationDestinations
import com.dev.bayan_ibrahim.bus_scheduler.ui.theme.BusSchedulerTheme


object HomeScreenDestinations: NavigationDestinations{
    override val route: String = "home"
}

@Composable
fun BusStopHomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onAddBusStop: () -> Unit = {},
    onBusStopClicked: (BusStopTravel) -> Unit = {},
) {
    val uiState by homeScreenViewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primary)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Bus Scheduler",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBusStop) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = MaterialTheme.colors.onSecondary,
                )
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ){
            item {
                Row (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                ){
                    Text(modifier = Modifier.weight(1f), text = "Stop name")
                    Text(text = "Arrival Time")
                }
            }
            items(uiState.stopList) {
                BusStopCard(stop = it, onCardClicked = onBusStopClicked)
            }
        }
    }
}

@Composable
private fun BusStopCard(
    modifier: Modifier = Modifier,
    stop: BusStopTravel,
    onCardClicked: (BusStopTravel) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Row (
            modifier = Modifier
                .clickable {
                    onCardClicked(stop)
                }
                .padding(16.dp)
                .fillMaxWidth(),
        ){
            Text(modifier = Modifier.weight(1f), text = stop.name)
            Text(text = stop.arrivalTime)
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun BusStopHomeScreenFunctionForPreview() {
    BusStopHomeScreen()
}

@Preview(showBackground = false)
@Composable
private fun BusStopHomeScreenPreviewLight() {
    BusSchedulerTheme (darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            BusStopHomeScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun BusStopHomeScreenPreviewDark() {
    BusSchedulerTheme (darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            BusStopHomeScreenFunctionForPreview()
        }
    }
}
