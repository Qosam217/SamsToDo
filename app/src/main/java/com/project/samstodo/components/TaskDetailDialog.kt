package com.project.samstodo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.project.samstodo.models.Task

@Composable
fun TaskDetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onCompletedChanged: (Boolean) -> Unit
){
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Task Detail")

                Spacer(Modifier.height(16.dp))

                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = task.description
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.isCompleted,
                        onCheckedChange = onCompletedChanged
                    )

                    Text("Completed")
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onDelete
                ) {
                    Text("Delete")
                }
                Spacer(Modifier.width(8.dp))

                Button(
                    onClick = onEdit
                ) {
                    Text("Edit")
                }
            }
        }
    }
}