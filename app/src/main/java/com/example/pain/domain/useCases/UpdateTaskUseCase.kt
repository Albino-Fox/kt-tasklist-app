package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import com.example.pain.domain.TaskUtils
import java.time.LocalDateTime

class UpdateTaskUseCase(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task, binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val desc = binding.description.text.toString()
        val dueDateTimeString = dueDateTime?.let { TaskUtils.formatDateTime(it) }

        task.name = name
        task.description = desc
        task.dueDateTimeString = dueDateTimeString

        repo.updateTask(task)
    }
}