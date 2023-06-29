package com.example.pain.presentation.components

import android.content.Context
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pain.R
import com.example.pain.databinding.TaskItemBinding
import java.time.format.DateTimeFormatter

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskItemBinding,
    private val clickListener: TaskClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun bindTask(taskViewData: TaskViewData) {
        binding.name.text = taskViewData.name
        binding.description.text = taskViewData.description

        if (taskViewData.isCompleted) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.completeButton.setImageResource(R.drawable.check_24)
            binding.completeButton.setColorFilter(ContextCompat.getColor(context, R.color.green_500))
        } else {
            binding.completeButton.setImageResource(R.drawable.uncheck_24)
            binding.completeButton.setColorFilter(ContextCompat.getColor(context, R.color.black))
        }

        binding.completeButton.setOnClickListener {
            clickListener.completeTask(taskViewData)
        }
        binding.taskItemContainer.setOnClickListener {
            clickListener.editTask(taskViewData)
        }

        if (taskViewData.isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.star_full_24)
            binding.favouriteButton.setColorFilter(ContextCompat.getColor(context, R.color.gold))
        } else {
            binding.favouriteButton.setImageResource(R.drawable.star_empty_24)
            binding.favouriteButton.setColorFilter(ContextCompat.getColor(context, R.color.black))
        }

        binding.favouriteButton.setOnClickListener {
            clickListener.favouriteTask(taskViewData)
        }

        binding.dueDateTime.text =
            if (taskViewData.dueDateTime != null) dateTimeFormat.format(taskViewData.dueDateTime) else ""
    }
}

