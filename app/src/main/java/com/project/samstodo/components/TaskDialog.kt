package com.project.samstodo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TaskDialog(
    onDismiss: () -> Unit,
    onCreate: (
            title: String,
            description: String,
            isDaily: Boolean
            ) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var isDaily by remember {
        mutableStateOf(true)
    }

    Dialog(
        onDismissRequest = onDismiss
    ){
        Card {
            Column {
                Text("New Task")

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text("Title")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row {
                    Checkbox(
                        checked = isDaily,
                        onCheckedChange = {
                            isDaily = it
                        }
                    )
                    Text(
                        text = "Daily Task"
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    label = {
                        Text("Description")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        onCreate(
                            title,
                            description,
                            isDaily
                        )
                        onDismiss()
                    }
                ) {
                    Text("Create")
                }

                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}