package com.example.tvmazeexample

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tvmazeexample.Repository.TvShowRepository
import com.example.tvmazeexample.Retrofit.RetrofitInstance
import com.example.tvmazeexample.ViewModels.DetailViewModel
import com.example.tvmazeexample.ViewModels.DetailViewModelFactory
import com.example.tvmazeexample.ViewModels.HomeViewModelFactory
import com.example.tvmazeexample.ViewModels.HomeViewModels

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // =========================
        // HOME
        // =========================
        composable("home") {

            val repository = TvShowRepository(
                RetrofitInstance.apiService
            )

            val viewModel: HomeViewModels = viewModel(
                factory = HomeViewModelFactory(repository)
            )

            val shows by viewModel.shows.collectAsState()

            HomeScreen(
                shows = shows,
                onShowClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }


        // =========================
        // DETAIL
        // =========================
        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val repository = TvShowRepository(
                RetrofitInstance.apiService
            )

            val detailViewModel: DetailViewModel = viewModel(
                factory = DetailViewModelFactory(repository)
            )

            val showState = detailViewModel.showDetail.collectAsState()

            LaunchedEffect(id) {
                detailViewModel.getShowDetail(id)
            }

            showState?.value?.let { showState ->
                DetailScreen(
                    show = showState,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}