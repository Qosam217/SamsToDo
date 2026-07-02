package com.project.samstodo.page

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.project.samstodo.components.BottomBar
import com.project.samstodo.components.TopBar
import com.project.samstodo.navigation.BottomTab
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.project.samstodo.components.AddTaskFab
import com.project.samstodo.components.TaskDetailDialog
import com.project.samstodo.components.TaskDialog
import com.project.samstodo.database.TaskDatabase
import com.project.samstodo.database.TaskRepository
import com.project.samstodo.models.Task
import com.project.samstodo.models.TaskDialogMode
import com.project.samstodo.viewmodel.TaskViewModel

@Composable
fun HomeScreen(viewModel: TaskViewModel) {
    var selectedTab by remember {
        mutableStateOf(BottomTab.TASK)
    }

    var showTaskDialog by remember {
        mutableStateOf(false)
    }

    var showEditDialog by remember {
        mutableStateOf(false)
    }

    val tasks by viewModel.tasks.collectAsState()

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
            mode = TaskDialogMode.CREATE,
            task = null,
            onDismiss = {
                showTaskDialog = false
            },
            onSubmit = { task ->
                viewModel.insert(task)
                showTaskDialog = false
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
                showTaskDetail = false
                showEditDialog = true
            },
            onDelete = {
                viewModel.delete(selectedTask!!)
                showTaskDetail = false
                selectedTask = null
            },
            onCompletedChanged = { completed ->
                val index = tasks.indexOfFirst {
                    it.id == selectedTask!!.id
                }
                if (index != -1){
                    viewModel.update(
                        selectedTask!!.copy(
                            isCompleted = completed
                        )
                    )
                    selectedTask = tasks[index]
                }
                showTaskDetail = false
                selectedTask = null
            }
        )
    }

    if(showEditDialog && selectedTask != null){
        TaskDialog(
            mode = TaskDialogMode.EDIT,
            task = selectedTask,
            onDismiss = {
                showEditDialog = false
            },
            onSubmit = { updatedTask ->

                val index = tasks.indexOfFirst {
                    it.id == updatedTask.id
                }

                if (index != -1) {
                    viewModel.update(updatedTask)
                    selectedTask = updatedTask
                }

                showEditDialog = false
            }
        )
    }
}