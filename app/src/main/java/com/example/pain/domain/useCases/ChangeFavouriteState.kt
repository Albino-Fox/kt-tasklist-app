package com.example.pain.domain.useCases

import com.example.pain.data.Task
import com.example.pain.domain.TaskRepo

class ChangeFavouriteState(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task) {
        task.isFavourite = !task.isFavourite
        repo.updateTask(task)
    }
}