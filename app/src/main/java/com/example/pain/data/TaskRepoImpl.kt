package com.example.pain.data

import com.example.pain.domain.TaskRepo
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

    override fun getFavouriteTasks() : Flow<List<Task>> {
        return dao.getFavouriteTasks()
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