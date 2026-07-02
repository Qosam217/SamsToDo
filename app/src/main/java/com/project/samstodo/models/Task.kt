package com.project.samstodo.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val taskType: TaskType,
    val dateFrom: Date? = null,
    val dateTo: Date? = null,
    val isCompleted: Boolean = false
)
