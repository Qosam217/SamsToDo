package com.project.samstodo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.samstodo.R

@Composable
fun AddTaskFab(
    onClick: () -> Unit
){
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp
        )
    ) {
        Image(
            painter = painterResource(
                R.drawable.add_task_fab
            ),
            contentDescription = "Add Task"
        )
    }
}