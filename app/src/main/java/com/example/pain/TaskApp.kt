package com.example.pain

import android.app.Application
import com.example.pain.data.TaskDatabase //ehh
import com.example.pain.data.TaskRepoImpl //ehh

class TaskApp: Application() {
    private val database by lazy {
        TaskDatabase.getDatabase(this)
    }
    val repo by lazy {
        TaskRepoImpl(database.taskDao())
    }
}