package com.example.pain.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pain.TaskApp
import com.example.pain.data.Task
import com.example.pain.databinding.FragmentFavouriteTasksBinding
import com.example.pain.presentation.components.TaskAdapter
import com.example.pain.presentation.components.TaskClickListener
import com.example.pain.presentation.components.TaskViewModelFactory
import com.example.pain.presentation.components.TaskViewModel

class FavouriteTasks : Fragment(), TaskClickListener {
    private lateinit var binding: FragmentFavouriteTasksBinding
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
        binding = FragmentFavouriteTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setRecyclerView() {
        val fragment = this
        taskViewModel.favouriteTasks.observe(this) {
            binding.taskListRecylerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = TaskAdapter(it, fragment)
            }
        }
    }

    override fun editTask(task: Task) {
        NewTaskSheet(task).show(parentFragmentManager, "newTaskTag")
    }

    override fun completeTask(task: Task) {
        taskViewModel.setCompleted(task)
    }

    override fun favouriteTask(task: Task) {
        taskViewModel.setFavourited(task)
    }
}