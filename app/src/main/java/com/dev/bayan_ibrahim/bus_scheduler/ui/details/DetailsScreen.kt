package com.dev.bayan_ibrahim.bus_scheduler.ui.details


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.DetailsUiState
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.entities.BusStopTravel
import com.dev.bayan_ibrahim.bus_scheduler.ui.AppViewModelProvider
import com.dev.bayan_ibrahim.bus_scheduler.ui.navigation.NavigationDestinations
import com.dev.bayan_ibrahim.bus_scheduler.ui.theme.BusSchedulerTheme

object DetailsScreenDestination: NavigationDestinations {
    override val route: String = "stop_details"
    val itemNameArg: String = "name"
    val routeWithArgs = "$route/{$itemNameArg}"
}
@Composable
fun BusStopDetailsScreen(
    modifier: Modifier = Modifier,
    detailsScreenViewModel: DetailsScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onAddTravel: (String) -> Unit = {},
    onItemClicked: (Int) -> Unit = {},
    navigateUp: () -> Unit = {}
) {
    val uiState by detailsScreenViewModel.detailsUiState.collectAsState()
    BackHandler(onBack = navigateUp)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
                 DetailsScreenTopAppBar(
                     navigateUp = navigateUp,
                     uiState = uiState,
                     delete = {
                         detailsScreenViewModel.delete()
                         navigateUp()
                     },
                 )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddTravel(uiState.name)
                }
            ) {
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
                    Text(modifier = Modifier.weight(1f), text = "Post Time")
                    Text(text = "Arrival Time")
                }
            }
            items(uiState.travelsList) {
                BusStopDetailsScreenCard(stop = it, onCardClicked = onItemClicked)
            }
        }
    }
}

@Composable
private fun DetailsScreenTopAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    uiState: DetailsUiState,
    delete: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = navigateUp) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "navigate up",
                tint = MaterialTheme.colors.onPrimary,
            )
        }
        Text(
            modifier = Modifier.weight(1f).padding(16.dp),
            text = uiState.name,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
        )
        IconButton(onClick = delete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "navigate up",
                tint = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun BusStopDetailsScreenCard(
    modifier: Modifier = Modifier,
    stop: BusStopTravel,
    onCardClicked: (Int) -> Unit,
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
                    onCardClicked(stop.id ?: -1)
                }
                .padding(16.dp)
                .fillMaxWidth(),
        ){
            Text(modifier = Modifier.weight(1f), text = stop.arrivalTime)
            Text(text = stop.arrivalTime)
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun BusStopDetailsScreenFunctionForPreview() {
    BusStopDetailsScreen()
}


@Preview(showBackground = false)
@Composable
private fun BusStopDetailsScreenPreviewLight() {
    BusSchedulerTheme (darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            BusStopDetailsScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun BusStopDetailsScreenPreviewDark() {
    BusSchedulerTheme (darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            BusStopDetailsScreenFunctionForPreview()
        }
    }
}
