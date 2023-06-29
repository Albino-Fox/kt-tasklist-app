package com.example.pain.domain.useCases

import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import java.time.LocalTime

class UpdateTask(
    private val repo: TaskRepo
) {
    suspend fun execute(task: Task, binding: FragmentNewTaskSheetBinding, dueTime: LocalTime?) {
        val name = binding.name.text.toString()
        if (name.isBlank()) return
        val desc = binding.name.text.toString()
        val dueTimeString = if (dueTime == null) null else Task.timeFormatter.format(dueTime)

        task.name = name
        task.desc = desc
        task.dueTimeString = dueTimeString

        repo.updateTask(task)
    }
}