package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.presentation.components.TaskViewData
import com.example.pain.presentation.components.mapper.TaskViewDataMapper
import java.time.LocalDateTime

class AddTaskUseCase(
    private val repo: TaskRepo,
    private val mapper: TaskViewDataMapper
) {
    suspend fun execute(binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val description = binding.description.text.toString()
        val taskViewData = TaskViewData(
            name,
            description,
            dueDateTime,
            isCompleted = false,
            isFavourite = false,
            id = -1 //invalid for new task
        )

        repo.insertTask(mapper.mapToNewTask(taskViewData))
    }
}
