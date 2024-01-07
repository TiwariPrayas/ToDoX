package com.example.myapplication.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.presentation.AppViewModelProvider
import com.example.myapplication.ui.presentation.screens.home.HomeDestination
import com.example.myapplication.ui.presentation.screens.home.HomeScreen
import com.example.myapplication.ui.presentation.screens.home.HomeViewModel
import com.example.myapplication.ui.presentation.screens.task.EditTaskDestination
import com.example.myapplication.ui.presentation.screens.task.EditTaskScreen
import com.example.myapplication.ui.presentation.screens.task.EditTaskViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            val viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            HomeScreen(viewModel = viewModel, onFabClick = {
                navController.navigate(route = EditTaskDestination.route)
            })
        }
        composable(route = EditTaskDestination.route) {
            val viewModel: EditTaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
            EditTaskScreen(
                viewModel = viewModel,
                onBackNavigationClick = {
                    navController.popBackStack()
                },
                onSubmitClick = {
                    viewModel.addTask()
                    navController.popBackStack()
                }
            )
        }
    }
}