package com.example.tvmazeexample

import HomeScreen
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.HtmlCompat
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

            val uiState by viewModel.uiState.collectAsState()

            HomeScreen(
                uiState = uiState,
                onRetry = {
                    viewModel.getShows()
                },
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

            val context = LocalContext.current

            val id = backStackEntry.arguments
                ?.getInt("id") ?: 0

            val repository = TvShowRepository(
                RetrofitInstance.apiService
            )

            val detailViewModel: DetailViewModel = viewModel(
                factory = DetailViewModelFactory(repository)
            )

            val showState =
                detailViewModel.showDetail.collectAsState()

            LaunchedEffect(id) {
                detailViewModel.getShowDetail(id)
            }

            // =========================
            // TAMPILKAN DETAIL
            // =========================

            showState.value?.let { show ->

                DetailScreen(
                    show = show,

                    onBackClick = {
                        navController.popBackStack()
                    },

                    // =========================
                    // SHARE
                    // =========================

                    onShareClick = {

                        // Bersihkan HTML dari summary
                        val summary = HtmlCompat
                            .fromHtml(
                                show.summary.orEmpty(),
                                HtmlCompat.FROM_HTML_MODE_LEGACY
                            )
                            .toString()
                            .trim()

                        // Isi yang akan dibagikan
                        val shareText = """
                            ${show.name ?: "Unknown"}
                            
                            $summary
                            
                            ${show.url ?: ""}
                        """.trimIndent()

                        // Intent Share
                        val shareIntent = Intent(
                            Intent.ACTION_SEND
                        ).apply {
                            type = "text/plain"

                            putExtra(
                                Intent.EXTRA_TEXT,
                                shareText
                            )
                        }

                        // Tampilkan Android Share Sheet
                        context.startActivity(
                            Intent.createChooser(
                                shareIntent,
                                "Share TV Show"
                            )
                        )
                    }
                )
            }
        }
    }
}