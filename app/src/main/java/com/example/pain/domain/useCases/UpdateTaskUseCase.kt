package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.presentation.components.TaskViewData
import com.example.pain.presentation.components.mapper.TaskViewDataMapper
import java.time.LocalDateTime

class UpdateTaskUseCase(
    private val repo: TaskRepo,
    private val mapper: TaskViewDataMapper
) {
    suspend fun execute(taskViewData: TaskViewData, binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val description = binding.description.text.toString()

        taskViewData.name = name
        taskViewData.description = description
        taskViewData.dueDateTime = dueDateTime

        val task = mapper.mapToTask(taskViewData)
        repo.updateTask(task)
    }
}
