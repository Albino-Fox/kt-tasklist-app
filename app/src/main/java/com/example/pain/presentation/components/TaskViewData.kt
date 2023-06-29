package com.example.pain.presentation.components

import java.time.LocalDateTime

data class TaskViewData(
    var name: String,
    var description: String,
    var dueDateTime: LocalDateTime?,
    var isCompleted: Boolean,
    var isFavourite: Boolean,
    val id: Int
)
