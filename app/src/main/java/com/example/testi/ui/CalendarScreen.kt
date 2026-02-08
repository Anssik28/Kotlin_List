package com.example.testi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testi.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.tasks.collectAsState()

    Column(modifier = Modifier.padding(32.dp)) {

        Text(
            text = "Calendar",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(ROUTE_HOME) }) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks.groupBy { it.dueDate }.toList()) { (date, tasksForDate) ->
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleMedium
                )
                tasksForDate.forEach { task ->
                    Text("• ${task.title}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
