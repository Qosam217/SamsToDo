package com.project.samstodo.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier
){
    val tasks = listOf(
        "Miss Herta",
        "Yao Guang",
        "Ruan Mei",
        "Cyrene",
        "Castorice",
        "Nihilux",
        "Evanescia",
        "Hysilens",
        "Aglaea",
        "Fugue",
        "Black Swan",
        "Topaz",
        "Archeron",
    )

    LazyColumn(
        modifier = modifier
    ) {
        items(tasks){ task ->
            Card{
                Text(task)
            }
        }
    }
}