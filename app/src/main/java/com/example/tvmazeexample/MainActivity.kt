package com.example.tvmazeexample

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.tvmazeexample.ui.theme.TVMazeExampleTheme
import com.example.tvmazeexample.ViewModels.HomeViewModels
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
import com.example.tvmazeexample.ViewModels.HomeViewModelFactory
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TVMazeExampleTheme {
                val viewModel: HomeViewModels = viewModel(
                    factory = HomeViewModelFactory(
                        TvShowRepository(RetrofitInstance.apiService)
                    )
                )
                val shows by viewModel.shows.collectAsState()
                val navController = rememberNavController()

                AppNavigation()
                }
            }
        }
    }

//modifier = Modifier
//.fillMaxWidth()
//.padding(horizontal = 20.dp)


