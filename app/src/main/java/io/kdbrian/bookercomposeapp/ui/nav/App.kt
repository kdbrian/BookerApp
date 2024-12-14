package io.kdbrian.bookercomposeapp.ui.nav

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.kdbrian.bookercomposeapp.data.util.Resource
import io.kdbrian.bookercomposeapp.presentation.viewmodel.CompaniesViewModel
import io.kdbrian.bookercomposeapp.presentation.viewmodel.RoutesViewModel
import io.kdbrian.bookercomposeapp.presentation.viewmodel.SchedulesViewModel
import io.kdbrian.bookercomposeapp.presentation.viewmodel.VehiclesViewModel
import io.kdbrian.bookercomposeapp.ui.screens.StatsScreen


@Composable
fun App(
    modifier: Modifier = Modifier,
    navController : NavHostController= rememberNavController(),
    companiesViewModel: CompaniesViewModel = viewModel<CompaniesViewModel>(),
    vehiclesViewModel: VehiclesViewModel = viewModel<VehiclesViewModel>(),
    routesViewModel: RoutesViewModel = viewModel<RoutesViewModel>(),
    schedulesViewModel: SchedulesViewModel = viewModel<SchedulesViewModel>()
) {

    val allCompanies by companiesViewModel.companies.collectAsState(initial = Resource.Loading())
    val allVehicles by vehiclesViewModel.allVehicles.collectAsState(initial = Resource.Loading())
    val allRoutes by routesViewModel.allRoutes.collectAsState(initial = Resource.Loading())
    val allSchedules by schedulesViewModel.allSchedules.collectAsState(initial = Resource.Loading())

    NavHost(
        navController = navController,
        startDestination = "stats"
    ){

        composable(route = "stats"){


            StatsScreen(
                modifier=modifier,
                allCompanies = allCompanies,
                allVehicles = allVehicles,
                allRoutes = allRoutes,
                allSchedules = allSchedules
            )
        }
    }



}

