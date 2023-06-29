package com.example.pain.data.model

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pain.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "task_table")
data class Task (
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "desc")
    var desc: String,

    @ColumnInfo(name = "dueTime")
    var dueTimeString: String?,

    @ColumnInfo(name = "completedDateString")
    var completedDateString: String?,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
) {

    private fun completedDate(): LocalDate? = if (completedDateString == null) null
        else LocalDate.parse(completedDateString, dateFormatter)
    fun dueTime(): LocalTime? = if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)

    fun isCompleted() = completedDate() != null
    fun checkIfIsFavourite() = isFavourite
    fun imageRes(): Int = if (isCompleted()) R.drawable.check_24 else R.drawable.uncheck_24
    fun imageColor(context: Context): Int = if (isCompleted()) green(context) else black(context)

    private fun green(context: Context) = ContextCompat.getColor(context, R.color.green_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object {
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}