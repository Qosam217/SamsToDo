package com.project.samstodo.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.samstodo.components.TaskCard
import com.project.samstodo.models.Task

@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit
){
    LazyColumn(
        modifier = modifier
    ) {
        items(tasks){ task ->
            TaskCard(
                title = task.title,
                description = task.description,
                dateFrom = task.dateFrom,
                dateTo = task.dateTo,
                taskType = task.taskType,
                isCompleted = task.isCompleted,
                onClick = {
                    onTaskClick(task)
                }
            )
        }
    }
}