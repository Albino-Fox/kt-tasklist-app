package com.example.pain.domain.useCases

import com.example.pain.data.Task
import com.example.pain.domain.TaskRepo
import java.time.LocalDate

class SetFavourited(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task) {
        task.isFavourite = !task.isFavourite
        repo.updateTask(task)
    }
}