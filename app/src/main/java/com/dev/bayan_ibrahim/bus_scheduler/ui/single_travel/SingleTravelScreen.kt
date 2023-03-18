package com.dev.bayan_ibrahim.bus_scheduler.ui


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.bayan_ibrahim.bus_scheduler.domain.model.SingleTravelUiState
import com.dev.bayan_ibrahim.bus_scheduler.ui.navigation.NavigationDestinations
import com.dev.bayan_ibrahim.bus_scheduler.ui.single_travel.SingleTravelScreenViewModel
import com.dev.bayan_ibrahim.bus_scheduler.ui.theme.BusSchedulerTheme

object SingleTravelDestination: NavigationDestinations{
    override val route: String = "stop_single_travel"
    val travelIdArg: String = "travel_id"
    val travelNameArg: String = "travel_name"
    val routeWithIdArg: String = "$route/{$travelIdArg}"
    val routeWithNameArg: String = "$route/{$travelNameArg}"
}
@Composable
fun SingleTravelScreen(
    modifier: Modifier = Modifier,
    travelViewModel: SingleTravelScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateUp: () -> Unit = {},
) {
    val uiState by travelViewModel.uiState.collectAsState()
    BackHandler(onBack = navigateUp)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
                 BusStopTobAppBar(
                     navigateUp = navigateUp,
                     uiState = uiState,
                     delete = {
                         travelViewModel.delete()
                         navigateUp()
                     }
                 )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if(travelViewModel.saveOrUpdate()){
                    navigateUp()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "save",
                    tint = MaterialTheme.colors.onSecondary,
                )
            }
        }
    ) {
        Column(
            modifier = modifier.padding(it),
        ) {
            SingleTravelEntry(
                label = "Name",
                value = uiState.travel.name,
                onValueChanged = {
                    travelViewModel.onNameChanged(it)
                },
                enabled = uiState.isNew,
            )
            SingleTravelEntry(
                label = "Arrival Time",
                value = uiState.travel.arrivalTime,
                onValueChanged = {
                    travelViewModel.onArrivalTimeChanged(it)
                }
            )
        }
    }


}
@Composable
private fun BusStopTobAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    delete: () -> Unit,
    uiState: SingleTravelUiState,
) {
    TopAppBar() {
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
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                text = if (uiState.isNew) {
                    "Add new Travel"
                } else {
                    "Update for ${uiState.travel.name}"
                },
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
}

@Composable
private fun SingleTravelEntry(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    enabled: Boolean = true,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            enabled = enabled,
            label = {
                Text(
                    text = label,
                )
            }
        )
        Divider(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun SingleTravelScreenFunctionForPreview() {
    SingleTravelScreen()
}

@Preview(showBackground = false)
@Composable
private fun SingleTravelScreenPreviewLight() {
    BusSchedulerTheme (darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SingleTravelScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun SingleTravelScreenPreviewDark() {
    BusSchedulerTheme (darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SingleTravelScreenFunctionForPreview()
        }
    }
}
