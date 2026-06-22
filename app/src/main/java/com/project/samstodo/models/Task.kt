package com.project.samstodo.models

import java.util.Date

data class Task(
    val title: String,
    val description: String,
    val taskType: TaskType,
    val dateFrom: Date? = null,
    val dateTo: Date? = null,
    val isCompleted: Boolean = false
)
