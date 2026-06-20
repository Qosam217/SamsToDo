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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){
    var selectedTab by remember {
        mutableStateOf(BottomTab.TASK)
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
        }
    ) { innerPadding ->
        when (selectedTab){
            BottomTab.TASK -> {
                TaskListScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            BottomTab.SCHEDULE -> {
                ScheduleScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}