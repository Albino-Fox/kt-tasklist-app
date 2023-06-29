package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import java.time.LocalDateTime

class UpdateTaskUseCase(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task, binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val desc = binding.desc.text.toString()
        val dueDateTimeString = if (dueDateTime != null) Task.dateTimeFormatter.format(dueDateTime) else null

        task.name = name
        task.desc = desc
        task.dueDateTimeString = dueDateTimeString

        repo.updateTask(task)
    }
}