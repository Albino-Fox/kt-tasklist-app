package com.example.pain.domain.useCases

import com.example.pain.domain.TaskRepo
import com.example.pain.data.model.Task
import java.time.LocalDate

class SetCompleted(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task) {
        if (!task.isCompleted()) {
            task.completedDateString = Task.dateFormatter.format(LocalDate.now())
        } else {
            task.completedDateString = null
        }

        repo.updateTask(task)
    }
}