package com.example.pain.presentation.components

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.pain.data.Task
import com.example.pain.databinding.TaskItemBinding
import com.example.pain.domain.TaskUtils
import java.time.format.DateTimeFormatter

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskItemBinding,
    private val clickListener: TaskClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun bindTask(task: Task) {
        binding.name.text = task.name
        binding.description.text = task.description

        if (task.isCompleted) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(TaskUtils.imageCheckerRes(task))
        binding.completeButton.setColorFilter(TaskUtils.imageCheckerColor(task, context))

        binding.completeButton.setOnClickListener {
            clickListener.completeTask(task)
        }
        binding.taskItemContainer.setOnClickListener {
            clickListener.editTask(task)
        }

        binding.favouriteButton.setImageResource(TaskUtils.imageFavRes(task))
        binding.favouriteButton.setColorFilter(TaskUtils.imageFavColor(task, context))

        binding.favouriteButton.setOnClickListener {
            clickListener.favouriteTask(task)
        }

        binding.dueDateTime.text =
            if (TaskUtils.dueDateTime(task) != null) dateTimeFormat.format(TaskUtils.dueDateTime(task)) else ""
    }
}
