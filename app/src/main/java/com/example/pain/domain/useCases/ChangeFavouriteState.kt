package com.example.pain.domain.useCases

import com.example.pain.domain.TaskRepo
import com.example.pain.presentation.components.TaskViewData
import com.example.pain.presentation.components.mapper.TaskViewDataMapper

class ChangeFavouriteState(
    private val repo: TaskRepo,
    private val mapper: TaskViewDataMapper
) {
    suspend fun execute(taskViewData: TaskViewData) {
        taskViewData.isFavourite = !taskViewData.isFavourite
        val task = mapper.mapToTask(taskViewData)
        repo.updateTask(task)
    }
}