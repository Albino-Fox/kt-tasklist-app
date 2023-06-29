package com.example.pain.data.repo

import com.example.pain.data.datasource.TaskDao
import com.example.pain.domain.TaskRepo
import com.example.pain.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepoImpl(
    private val dao: TaskDao
) : TaskRepo {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override fun getUncompletedTasks() : Flow<List<Task>> {
       return dao.getUncompletedTasks()
    }

    override fun getCompletedTasks() : Flow<List<Task>> {
        return dao.getCompletedTasks()
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }
}