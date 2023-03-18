package com.dev.bayan_ibrahim.bus_scheduler


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.bayan_ibrahim.bus_scheduler.ui.navigation.BusStopNavGraph
import com.dev.bayan_ibrahim.bus_scheduler.ui.theme.BusSchedulerTheme

@Composable
fun BusStopApp(
    modifier: Modifier = Modifier,
) {
    BusStopNavGraph()
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun BusStopAppFunctionForPreview() {
    BusStopApp()
}

@Preview(showBackground = false)
@Composable
private fun BusStopAppPreviewLight() {
    BusSchedulerTheme (darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            BusStopAppFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun BusStopAppPreviewDark() {
    BusSchedulerTheme (darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            BusStopAppFunctionForPreview()
        }
    }
}
