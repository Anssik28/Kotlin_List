package com.example.testi.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.testi.domain.Task
import com.example.testi.domain.mockTasks

class TaskViewModel : ViewModel() {

    private val allTasks = mutableStateOf<List<Task>>(emptyList())
    var tasks = mutableStateOf<List<Task>>(emptyList())
        private set

    init {
        allTasks.value = mockTasks
        tasks.value = mockTasks
    }

    fun addTask(task: Task) {
        allTasks.value = allTasks.value + task
        tasks.value = allTasks.value
    }

    fun toggleDone(id: Int) {
        allTasks.value = allTasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
        tasks.value = allTasks.value
    }

    fun removeTask(id: Int) {
        allTasks.value = allTasks.value.filterNot { it.id == id }
        tasks.value = allTasks.value
    }

    fun filterByDone(done: Boolean) {
        tasks.value = allTasks.value.filter { it.done == done }
    }

    fun showAll() {
        tasks.value = allTasks.value
    }

    fun sortByDueDate(){
        tasks.value = tasks.value.sortedBy { it.dueDate }
    }

    fun sortByPriority(){
        tasks.value = tasks.value.sortedBy { it.priority }
    }
}
