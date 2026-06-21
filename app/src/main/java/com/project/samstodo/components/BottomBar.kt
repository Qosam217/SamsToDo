package com.project.samstodo.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.project.samstodo.R
import com.project.samstodo.navigation.BottomTab

@Composable
fun BottomBar(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
){
    NavigationBar{
        NavigationBarItem(
            selected = selectedTab == BottomTab.TASK,
            onClick = {
                onTabSelected(BottomTab.TASK)
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.tasklist),
                    contentDescription = "Task"
                )
            }
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.SCHEDULE,
            onClick = {
                onTabSelected(BottomTab.SCHEDULE)
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.schedule),
                    contentDescription = "Schedule"
                )
            }
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.NOTE,
            onClick = {
                onTabSelected(BottomTab.NOTE)
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.note),
                    contentDescription = "Note"
                )
            }
        )
    }
}