package com.example.pain.domain.useCases

import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task

class DeleteTaskUseCase(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task) {
        repo.deleteTask(task)
    }
}