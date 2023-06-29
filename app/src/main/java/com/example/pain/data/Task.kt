package com.example.pain.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
