package com.example.pain.presentation.components

interface TaskClickListener {
    fun editTask(task: TaskViewData)
    fun completeTask(task: TaskViewData)
    fun favouriteTask(task: TaskViewData)
}