package com.example.testi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testi.domain.Task
import com.example.testi.viewmodel.TaskViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import com.example.testi.ui.ROUTE_HOME
import com.example.testi.ui.ROUTE_CALENDAR
import com.example.testi.ui.ROUTE_SETTINGS




@Composable
fun HomeScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }



    Column(modifier = Modifier.padding(32.dp)) {

        Text(
            text = "Tasks",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(onClick = { navController.navigate(ROUTE_HOME) }) {
                Text("Home")
            }
            Button(onClick = { navController.navigate(ROUTE_CALENDAR) }) {
                Text("Calendar")
            }
            Button(onClick = { navController.navigate(ROUTE_SETTINGS) }) {
                Text("Settings")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { taskViewModel.showAll() }) {
                Text("All")
            }
            Button(onClick = { taskViewModel.filterByDone(true) }) {
                Text("Done")
            }
            Button(onClick = { showAddDialog = true }) {
                Text("+")
            }
        }

        Box {
            Button(onClick = { expanded = true }) {
                Text("Sort")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Due date") },
                    onClick = {
                        taskViewModel.sortByDueDate()
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Priority") },
                    onClick = {
                        taskViewModel.sortByPriority()
                        expanded = false
                    }
                )
            }
        }

        LazyColumn {
            items(tasks) { task ->
                TaskRow(
                    task = task,
                    onToggle = { taskViewModel.toggleDone(task.id) },
                    onDelete = { taskViewModel.removeTask(task.id) },
                    onClick = { selectedTask = task }
                )
            }
        }
    }
    selectedTask?.let { task ->
        DetailScreen(
            task = task,
            onUpdate = { taskViewModel.updateTask(it) },
            onDelete = { taskViewModel.removeTask(task.id) },
            onDismiss = { selectedTask = null }
        )
    }
    if (showAddDialog) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var dueDate by remember { mutableStateOf("") }
        var priority by remember { mutableStateOf("1")}

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            confirmButton = {
                Button(onClick = {
                    if (title.isNotBlank()) {
                        taskViewModel.addTask(
                            Task(
                                id = tasks.size + 1,
                                title = title,
                                description = description,
                                priority = priority.toIntOrNull() ?: 1,
                                dueDate = dueDate,
                                done = false
                            )
                        )
                        showAddDialog = false
                    }
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = dueDate,
                        onValueChange = { dueDate = it },
                        label = { Text("Due date (YYYY-MM-DD)") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = priority,
                        onValueChange = { priority = it },
                        label = { Text("Priority (1–5)") }
                    )
                }
            }
        )
    }
}
@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick()},
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = task.done,
                onCheckedChange = { onToggle() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = task.title)
        }
        Button(onClick = onDelete) {
            Text("Delete")
        }
    }
}
