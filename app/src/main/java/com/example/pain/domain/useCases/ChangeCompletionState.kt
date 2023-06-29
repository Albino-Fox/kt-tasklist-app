package com.example.pain.domain.useCases

import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task

class ChangeCompletionState(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task) {
        task.isCompleted = !task.isCompleted
        repo.updateTask(task)
    }
}