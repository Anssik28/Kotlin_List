package com.example.testi.domain

fun addTask(list: MutableList<Task>, task: Task){
    list.add(task)
}

fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) {
            task.copy(done = !task.done)
        } else {
            task
        }
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: MutableList<Task>) {
    list.sortBy { it.dueDate }
}
