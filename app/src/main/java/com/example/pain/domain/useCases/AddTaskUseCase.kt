package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import com.example.pain.domain.TaskUtils
import java.time.LocalDateTime

class AddTaskUseCase(
    private val repo: TaskRepo
) {
    suspend fun execute(binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val description = binding.description.text.toString()
        val dueDateTimeString = dueDateTime?.let { TaskUtils.formatDateTime(it) }

        val newTask = Task(name, description, dueDateTimeString, isCompleted = false, isFavourite = false)
        repo.insertTask(newTask)
    }
}