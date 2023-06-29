package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import java.time.LocalTime

class AddTaskUseCase(
    private val repo: TaskRepo
) {
    suspend fun execute(binding: FragmentNewTaskSheetBinding, dueTime: LocalTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val desc = binding.desc.text.toString()
        val dueTimeString = if (dueTime == null) null else Task.timeFormatter.format(dueTime)

        val newTask = Task(name, desc, dueTimeString, isCompleted = false, isFavourite = false)
        repo.insertTask(newTask)
    }
}