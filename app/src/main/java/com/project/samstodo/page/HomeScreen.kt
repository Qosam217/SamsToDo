package com.project.samstodo.page

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.project.samstodo.components.BottomBar
import com.project.samstodo.components.TopBar
import com.project.samstodo.navigation.BottomTab
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.project.samstodo.components.AddTaskFab
import com.project.samstodo.components.TaskDetailDialog
import com.project.samstodo.components.TaskDialog
import com.project.samstodo.models.Task

@Composable
fun HomeScreen() {
    var selectedTab by remember {
        mutableStateOf(BottomTab.TASK)
    }

    var showTaskDialog by remember {
        mutableStateOf(false)
    }

    val tasks = remember {
        mutableStateListOf<Task>()
    }

    var selectedTask by remember {
        mutableStateOf<Task?>(null)
    }

    var showTaskDetail by remember{
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "SamsToDo",
                onSettingsClick = {
                    // nav to settings screen
                }
            )
        },
        bottomBar = {
            BottomBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )
        },
        floatingActionButton = {
            AddTaskFab(
                onClick = {
                    showTaskDialog = true
                }
            )
        }
    ) { innerPadding ->
        when (selectedTab) {
            BottomTab.TASK -> {
                TaskListScreen(
                    modifier = Modifier.padding(innerPadding),
                    tasks = tasks,
                    onTaskClick = { task ->
                        selectedTask = task
                        showTaskDetail = true
                    }
                )
            }

            BottomTab.SCHEDULE -> {
                ScheduleScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            BottomTab.NOTE -> {
                NoteScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

    if (showTaskDialog) {
        TaskDialog(
            onDismiss = {
                showTaskDialog = false
            },
            onCreate = { title, description, taskType, dateFrom, dateTo ->
                tasks.add(
                    Task(
                        title = title,
                        description = description,
                        dateFrom = dateFrom,
                        dateTo = dateTo,
                        taskType = taskType
                    )
                )
            }
        )
    }

    if (showTaskDetail && selectedTask != null){
        TaskDetailDialog(
            task = selectedTask!!,
            onDismiss = {
                showTaskDetail = false
            },
            onEdit = {

            },

            onDelete = {

            },

            onCompletedChanged = {

            }
        )
    }
}