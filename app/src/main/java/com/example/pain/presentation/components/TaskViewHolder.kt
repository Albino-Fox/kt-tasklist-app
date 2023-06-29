package com.example.pain.presentation.components

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.pain.data.Task
import com.example.pain.databinding.TaskItemBinding
import java.time.format.DateTimeFormatter

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskItemBinding,
    private val clickListener: TaskClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun bindTask(task: Task) {
        binding.name.text = task.name
        binding.desc.text = task.desc

        if (task.isCompleted) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(task.imageCheckerRes())
        binding.completeButton.setColorFilter(task.imageCheckerColor(context))

        binding.completeButton.setOnClickListener {
            clickListener.completeTask(task)
        }
        binding.taskItemContainer.setOnClickListener {
            clickListener.editTask(task)
        }

        binding.favouriteButton.setImageResource(task.imageFavRes())
        binding.favouriteButton.setColorFilter(task.imageFavColor(context))

        binding.favouriteButton.setOnClickListener {
            clickListener.favouriteTask(task)
        }

        binding.dueDateTime.text =
            if (task.dueDateTime() != null) dateTimeFormat.format(task.dueDateTime()) else ""

    }
}
