package com.example.pain.presentation.components

import androidx.lifecycle.*
import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.data.Task
import com.example.pain.domain.useCases.*
import kotlinx.coroutines.launch
import java.time.LocalTime

class TaskViewModel(private val repo: TaskRepo): ViewModel() {
    private val addTaskUseCase = AddTaskUseCase(repo)
    private val getTasksUseCase = GetTasksUseCase(repo)
    private val getUncompletedTasksUseCase = GetUncompletedTasksUseCase(repo)
    private val getCompletedTasksUseCase = GetCompletedTasksUseCase(repo)
    private val getFavouriteTasksUseCase = GetFavouriteTasksUseCase(repo)
    private val updateTaskUseCase = UpdateTaskUseCase(repo)
    private val changeCompleteState = ChangeCompleteState(repo)
    private val changeFavouriteState = ChangeFavouriteState(repo)
    private val deleteTaskUseCase = DeleteTaskUseCase(repo)

    var tasks : LiveData<List<Task>> = getTasksUseCase.execute().asLiveData()
    var uncompletedTasks : LiveData<List<Task>> = getUncompletedTasksUseCase.execute().asLiveData()
    var completedTasks : LiveData<List<Task>> = getCompletedTasksUseCase.execute().asLiveData()
    var favouriteTasks : LiveData<List<Task>> = getFavouriteTasksUseCase.execute().asLiveData()

    fun addTask(binding: FragmentNewTaskSheetBinding, dueTime: LocalTime?) = viewModelScope.launch {
        addTaskUseCase.execute(binding, dueTime)
    }

    fun updateTask(task: Task, binding: FragmentNewTaskSheetBinding, dueTime: LocalTime?) = viewModelScope.launch {
        updateTaskUseCase.execute(task, binding, dueTime)
    }

    fun setCompleted(task: Task) = viewModelScope.launch {
        changeCompleteState.execute(task)
    }

    fun setFavourited(task: Task) = viewModelScope.launch{
        changeFavouriteState.execute(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        deleteTaskUseCase.execute(task)
    }
}

