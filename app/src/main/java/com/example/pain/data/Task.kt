package com.example.pain.data

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pain.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "task_table")
data class Task(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "dueDateTime")
    var dueDateTimeString: String?,

    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
) {

    fun dueDateTime(): LocalDateTime? =
        if (dueDateTimeString == null) null else LocalDateTime.parse(dueDateTimeString, dateTimeFormatter)

    fun imageCheckerRes(): Int = if (isCompleted) R.drawable.check_24 else R.drawable.uncheck_24
    fun imageCheckerColor(context: Context): Int = if (isCompleted) green(context) else black(context)

    fun imageFavRes(): Int = if (isFavourite) R.drawable.star_full_24 else R.drawable.star_empty_24
    fun imageFavColor(context: Context): Int = if (isFavourite) gold(context) else black(context)

    private fun green(context: Context) = ContextCompat.getColor(context, R.color.green_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
    private fun gold(context: Context) = ContextCompat.getColor(context, R.color.gold)

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    }
}
