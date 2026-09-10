package com.example.tvmazeexample

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.example.tvmazeexample.ui.theme.TVMazeExampleTheme
import com.example.tvmazeexample.ViewModels.HomeViewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.example.tvmazeexample.Retrofit.ApiConfig
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModels by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TVMazeExampleTheme {
                val shows by viewModel.shows.collectAsState()
                HomeScreen(
                    shows = shows,
                    onShowClick = { id ->
                        // Nanti untuk membuka halaman detail
                    }
                )
                }


            }
        }
    }


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TVMazeExampleTheme {
//        Greeting("Android")
//    }
//}