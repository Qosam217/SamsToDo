package com.project.samstodo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.project.samstodo.models.TaskType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TaskCard(
    title: String,
    description: String,
    dateTo: Date?,
    dateFrom: Date?,
    taskType: TaskType,
    isCompleted: Boolean,
    onClick: () -> Unit = {}
){
    val formatter = SimpleDateFormat(
        "dd MMM",
        Locale.getDefault()
    )

    val dateLabel = when {
        taskType == TaskType.DAILY -> "Daily"

        dateFrom != null && dateTo != null ->
            "${formatter.format(dateFrom)} - ${formatter.format(dateTo)}"

        else -> "-"
    }

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable{ onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if(isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
                Text(
                    text = description
                )
            }
            Spacer(
                modifier = Modifier.width(12.dp)
            )
            Text(
                text = dateLabel
            )
        }
    }
}