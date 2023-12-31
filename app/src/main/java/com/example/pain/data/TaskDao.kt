package com.example.pain.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE isCompleted == 0")
    fun getUncompletedTasks() : Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE isCompleted != 0")
    fun getCompletedTasks() : Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE isFavourite != 0")
    fun getFavouriteTasks() : Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}