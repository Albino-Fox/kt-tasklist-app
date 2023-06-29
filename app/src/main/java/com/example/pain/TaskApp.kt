package com.example.pain

import android.app.Application
import com.example.pain.data.TaskDatabase
import com.example.pain.data.TaskRepoImpl

class TaskApp: Application() {
    private val database by lazy { TaskDatabase.getDatabase(this) }
    val repo by lazy { TaskRepoImpl(database.taskDao()) }
}