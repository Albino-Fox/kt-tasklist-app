package com.example.pain.presentation.components

import com.example.pain.data.Task

interface TaskClickListener {
    fun editTask(task: Task)
    fun completeTask(task: Task)
}