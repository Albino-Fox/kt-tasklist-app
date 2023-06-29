package com.example.pain.presentation.components

import androidx.lifecycle.*
import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.domain.TaskRepo
import com.example.pain.domain.useCases.*
import com.example.pain.presentation.components.mapper.TaskViewDataMapper
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class TaskViewModel(private val repo: TaskRepo): ViewModel() {
    private val taskViewDataMapper = TaskViewDataMapper()
    private val addTaskUseCase = AddTaskUseCase(repo, taskViewDataMapper)
    private val getTasksUseCase = GetTasksUseCase(repo, taskViewDataMapper)
    private val getUncompletedTasksUseCase = GetUncompletedTasksUseCase(repo, taskViewDataMapper)
    private val getCompletedTasksUseCase = GetCompletedTasksUseCase(repo, taskViewDataMapper)
    private val getFavouriteTasksUseCase = GetFavouriteTasksUseCase(repo, taskViewDataMapper)
    private val updateTaskUseCase = UpdateTaskUseCase(repo, taskViewDataMapper)
    private val changeCompletionState = ChangeCompletionState(repo, taskViewDataMapper)
    private val changeFavouriteState = ChangeFavouriteState(repo, taskViewDataMapper)
    private val deleteTaskUseCase = DeleteTaskUseCase(repo, taskViewDataMapper)

    var tasks : LiveData<List<TaskViewData>> = getTasksUseCase.execute().asLiveData()
    var uncompletedTasks : LiveData<List<TaskViewData>> = getUncompletedTasksUseCase.execute().asLiveData()
    var completedTasks : LiveData<List<TaskViewData>> = getCompletedTasksUseCase.execute().asLiveData()
    var favouriteTasks : LiveData<List<TaskViewData>> = getFavouriteTasksUseCase.execute().asLiveData()

    fun addTask(binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) = viewModelScope.launch {
        addTaskUseCase.execute(binding, dueDateTime)
    }

    fun updateTask(task: TaskViewData, binding: FragmentNewTaskSheetBinding, dueDateTime: LocalDateTime?) = viewModelScope.launch {
        updateTaskUseCase.execute(task, binding, dueDateTime)
    }

    fun deleteTask(task: TaskViewData) = viewModelScope.launch {
        deleteTaskUseCase.execute(task)
    }

    fun setCompleted(task: TaskViewData) = viewModelScope.launch {
        changeCompletionState.execute(task)
    }

    fun setFavourited(task: TaskViewData) = viewModelScope.launch{
        changeFavouriteState.execute(task)
    }
}
