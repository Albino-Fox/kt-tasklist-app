package com.example.pain.domain

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.pain.R
import com.example.pain.data.Task
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TaskUtils {

    fun dueDateTime(task: Task): LocalDateTime? =
        task.dueDateTimeString?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME) }

    fun formatDateTime(dateTime: LocalDateTime): String =
        DateTimeFormatter.ISO_DATE_TIME.format(dateTime)

    fun imageCheckerRes(task: Task): Int = if (task.isCompleted) R.drawable.check_24 else R.drawable.uncheck_24
    fun imageCheckerColor(task: Task, context: Context): Int = if (task.isCompleted) green(context) else black(context)

    fun imageFavRes(task: Task): Int = if (task.isFavourite) R.drawable.star_full_24 else R.drawable.star_empty_24
    fun imageFavColor(task: Task, context: Context): Int = if (task.isFavourite) gold(context) else black(context)

    private fun green(context: Context) = ContextCompat.getColor(context, R.color.green_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
    private fun gold(context: Context) = ContextCompat.getColor(context, R.color.gold)

}
