package com.project.samstodo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.project.samstodo.models.TaskType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TaskDialog(
    onDismiss: () -> Unit,
    onCreate: (
            title: String,
            description: String,
            taskType: TaskType,
            dateFrom: Date?,
            dateTo: Date?
            ) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var taskType by remember{
        mutableStateOf(TaskType.DAILY)
    }

    var dateFrom by remember {
        mutableStateOf<Date?>(null)
    }

    var dateTo by remember {
        mutableStateOf<Date?>(null)
    }

    var showDateRangePicker by remember {
        mutableStateOf(false)
    }

    val formatter = SimpleDateFormat(
        "dd MMM yyyy",
        Locale.getDefault()
    )

    Dialog(
        onDismissRequest = onDismiss
    ){
        Card (
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                Text(
                    text = "Create Task"
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

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

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = taskType == TaskType.DAILY,
                        onCheckedChange = {
                            if (it){
                                taskType = TaskType.DAILY
                                dateFrom = null
                                dateTo = null
                            }else{
                                taskType = TaskType.RANGE
                            }
                        }
                    )
                    Text(
                        text = "Daily Task"
                    )
                }

                OutlinedButton(
                    onClick = {
                        showDateRangePicker = true
                    },
                    enabled = taskType != TaskType.DAILY,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = when {
                            dateFrom != null && dateTo != null ->
                                "${formatter.format(dateFrom!!)} - ${formatter.format(dateTo!!)}"

                            else ->
                                "Select Date Range"
                        }
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
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Cancel")
                    }

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Button(

                        onClick = {
                            if (title.isBlank()) {
                                return@Button
                            }

                            if ((taskType != TaskType.DAILY) &&
                                (dateFrom == null || dateTo == null)
                            ) {
                                return@Button
                            }

                            onCreate(
                                title,
                                description,
                                taskType,
                                dateFrom,
                                dateTo
                            )
                            onDismiss()
                        }
                    ) {
                        Text("Create")
                    }
                }
            }
        }
    }
    if(showDateRangePicker){
        val dateRangeState = rememberDateRangePickerState()

        DatePickerDialog(
            onDismissRequest = {
                showDateRangePicker = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        dateRangeState.selectedStartDateMillis?.let {
                            dateFrom = Date(it)
                        }

                        dateRangeState.selectedEndDateMillis?.let {
                            dateTo = Date(it)
                        }

                        showDateRangePicker = false
                    }
                ) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDateRangePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DateRangePicker(
                state = dateRangeState
            )
        }
    }

}