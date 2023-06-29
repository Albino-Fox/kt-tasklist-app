package com.example.pain.presentation.components

import androidx.lifecycle.*
import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import com.example.pain.domain.useCases.*
import kotlinx.coroutines.launch
import java.time.LocalTime

class TaskViewModel(private val repo: TaskRepo): ViewModel() {
    private val addTask = AddTask(repo)
    private val getTasks = GetTasks(repo)
    private val getUncompletedTasks = GetUncompletedTasks(repo)
    private val getCompletedTasks = GetCompletedTasks(repo)
    private val getFavouriteTasks = GetFavouriteTasks(repo)
    private val updateTask = UpdateTask(repo)
    private val setCompleted = SetCompleted(repo)
    private val deleteTask = DeleteTask(repo)

    var tasks : LiveData<List<Task>> = getTasks.execute().asLiveData()
    var uncompletedTasks : LiveData<List<Task>> = getUncompletedTasks.execute().asLiveData()
    var completedTasks : LiveData<List<Task>> = getCompletedTasks.execute().asLiveData()
    var favouriteTasks : LiveData<List<Task>> = getFavouriteTasks.execute().asLiveData()

    fun addTask(binding: FragmentNewTaskSheetBinding, dueTime: LocalTime?) = viewModelScope.launch {
        addTask.execute(binding, dueTime)
    }

    fun updateTask(task: Task, binding: FragmentNewTaskSheetBinding, dueTime: LocalTime?) = viewModelScope.launch {
        updateTask.execute(task, binding, dueTime)
    }

    fun setCompleted(task: Task) = viewModelScope.launch {
        setCompleted.execute(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        deleteTask.execute(task)
    }
}

