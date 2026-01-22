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

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks by taskViewModel.tasks
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(32.dp)) {

        Text(
            text = "Tasks",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("New task") }
            )
            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        taskViewModel.addTask(
                            Task(
                                id = tasks.size + 1,
                                title = newTaskTitle,
                                description = "",
                                priority = 1,
                                dueDate = "2026-01-30",
                                done = false
                            )
                        )
                        newTaskTitle = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    onDelete = { taskViewModel.removeTask(task.id) }
                )
            }
        }
    }
}
@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
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
