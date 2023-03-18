package com.dev.bayan_ibrahim.bus_scheduler.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.bayan_ibrahim.bus_scheduler.ui.SingleTravelDestination
import com.dev.bayan_ibrahim.bus_scheduler.ui.SingleTravelScreen
import com.dev.bayan_ibrahim.bus_scheduler.ui.details.BusStopDetailsScreen
import com.dev.bayan_ibrahim.bus_scheduler.ui.details.DetailsScreenDestination
import com.dev.bayan_ibrahim.bus_scheduler.ui.home.BusStopHomeScreen
import com.dev.bayan_ibrahim.bus_scheduler.ui.home.HomeScreenDestinations


@Composable
fun BusStopNavGraph(
    modifier: Modifier = Modifier,
) {
    val controller = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = HomeScreenDestinations.route
    ) {
        composable(route = HomeScreenDestinations.route) {
            BusStopHomeScreen(
                onAddBusStop = {
                    // adding new Bus stop
                    controller.navigate(SingleTravelDestination.route)
                },
                onBusStopClicked = {
                    // showing existed Bus stop
                    controller.navigate("${DetailsScreenDestination.route}/${it.name}")
                }
            )
        }
        composable(
            route = DetailsScreenDestination.routeWithArgs,
            arguments = listOf(
                navArgument(name = DetailsScreenDestination.itemNameArg) {
                    type = NavType.StringType
                }
            )
        ) {
            BusStopDetailsScreen(
                onAddTravel = { name ->
                    controller.navigate("${SingleTravelDestination.route}/${name}")
                },
                onItemClicked = { id ->
                    controller.navigate("${SingleTravelDestination.route}/${id}")
                },
                navigateUp = {
                    controller.navigateUp()
                }
            )
        }
        composable(
            route = SingleTravelDestination.route,
        ) {
            SingleTravelScreen(
                navigateUp = {
                    controller.navigateUp()
                },
            )
        }
        composable(
            route = SingleTravelDestination.routeWithIdArg,
            arguments = listOf(
                navArgument(name = SingleTravelDestination.travelIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            SingleTravelScreen(
                navigateUp = {
                    controller.navigateUp()
                },
            )
        }
        composable(
            route = SingleTravelDestination.routeWithNameArg,
            arguments = listOf(
                navArgument(name = SingleTravelDestination.travelNameArg) {
                    type = NavType.StringType
                }
            )
        ) {
            SingleTravelScreen(
                navigateUp = {
                    controller.navigateUp()
                },
            )
        }
    }
}