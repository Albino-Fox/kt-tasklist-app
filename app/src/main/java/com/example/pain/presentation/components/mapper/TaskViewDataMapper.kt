package com.example.pain.presentation.components.mapper

import com.example.pain.data.Task
import com.example.pain.presentation.components.TaskViewData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskViewDataMapper {

    fun mapToTaskViewData(task: Task): TaskViewData {
        return TaskViewData(
            name = task.name,
            description = task.description,
            dueDateTime = task.dueDateTimeString?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME) },
            isCompleted = task.isCompleted,
            isFavourite = task.isFavourite,
            id = task.id
        )
    }

    fun mapToTask(taskViewData: TaskViewData): Task {
        return Task(
            id = taskViewData.id,
            name = taskViewData.name,
            description = taskViewData.description,
            dueDateTimeString = taskViewData.dueDateTime?.let {DateTimeFormatter.ISO_DATE_TIME.format(it)},
            isCompleted = taskViewData.isCompleted,
            isFavourite = taskViewData.isFavourite
        )
    }
}
