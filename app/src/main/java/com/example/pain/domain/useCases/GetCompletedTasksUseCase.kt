package com.example.pain.domain.useCases

import com.example.pain.domain.TaskRepo
import com.example.pain.presentation.components.TaskViewData
import com.example.pain.presentation.components.mapper.TaskViewDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCompletedTasksUseCase(
    private val repo: TaskRepo,
    private val mapper: TaskViewDataMapper
) {
    fun execute() : Flow<List<TaskViewData>> {
        return repo.getCompletedTasks().map { tasks -> tasks.map { mapper.mapToTaskViewData(it) } }
    }
}
