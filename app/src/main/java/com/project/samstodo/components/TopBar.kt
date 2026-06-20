package com.project.samstodo.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.project.samstodo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onSettingsClick: () -> Unit = {}
){
    TopAppBar(
        title = {
            Text(title)
        },
        actions = {
            IconButton(onClick = onSettingsClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.settings_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = "Settings"
                )
            }
        }
    )
}