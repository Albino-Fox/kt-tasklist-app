package com.example.pain

import android.app.Application
import com.example.pain.data.datasource.TaskDatabase
import com.example.pain.data.repo.TaskRepoImpl

class TaskApp: Application() {
    private val database by lazy { TaskDatabase.getDatabase(this) }
    val repo by lazy { TaskRepoImpl(database.taskDao()) }
}