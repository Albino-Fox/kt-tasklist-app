package com.example.pain.presentation.components

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.pain.databinding.TaskCellBinding
import com.example.pain.data.model.Task
import java.time.format.DateTimeFormatter

class TaskViewHolder(
    private val context: Context,
    private val binding: TaskCellBinding,
    private val clickListener: TaskClickListener
): RecyclerView.ViewHolder(binding.root) {

    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTask(task: Task) {
        binding.name.text = task.name

        if (task.isCompleted()) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(task.imageRes())
        binding.completeButton.setColorFilter(task.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completeTask(task)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTask(task)
        }

        if (task.dueTime() != null)
            binding.dueTime.text = timeFormat.format(task.dueTime())
        else
            binding.dueTime.text = ""
    }
}