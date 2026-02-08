package com.example.testi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testi.ui.*
import com.example.testi.ui.theme.TestiTheme
import com.example.testi.viewmodel.TaskViewModel




class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestiTheme {
                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME
                ) {
                    composable(ROUTE_HOME) {
                        HomeScreen(navController, taskViewModel)
                    }
                    composable(ROUTE_CALENDAR) {
                        CalendarScreen(navController, taskViewModel)
                    }
                    composable(ROUTE_SETTINGS) {
                        SettingsScreen(navController)
                    }
                }
            }
        }
    }
}