package com.example.pain.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pain.TaskApp
import com.example.pain.databinding.FragmentUncompletedTasksBinding
import com.example.pain.presentation.components.*

class UncompletedTasks : Fragment(), TaskClickListener {
    private lateinit var binding: FragmentUncompletedTasksBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((requireActivity().application as TaskApp).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUncompletedTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setRecyclerView() {
        val fragment = this
        taskViewModel.uncompletedTasks.observe(this) {
            binding.taskListRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = TaskAdapter(it, fragment)
            }
        }
    }

    override fun editTask(task: TaskViewData) {
        NewTaskSheet(task).show(parentFragmentManager, "newTaskTag")
    }

    override fun completeTask(task: TaskViewData) {
        taskViewModel.setCompleted(task)
    }

    override fun favouriteTask(task: TaskViewData) {
        taskViewModel.setFavourited(task)
    }
}