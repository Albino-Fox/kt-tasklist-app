package com.example.pain.domain.useCases

import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import kotlinx.coroutines.flow.Flow

class GetUncompletedTasks(
    private val repo: TaskRepo
) {
    fun execute() : Flow<List<Task>> {
        return repo.getUncompletedTasks()
    }
}