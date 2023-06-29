package com.example.pain.presentation.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pain.domain.TaskRepo

class TaskModelFactory(
    private val repo: TaskRepo
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repo) as T

        throw java.lang.IllegalArgumentException("unknown class")
    }
}