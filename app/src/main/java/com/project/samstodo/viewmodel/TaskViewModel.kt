package com.project.samstodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.samstodo.database.TaskRepository
import com.project.samstodo.models.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks = repository.tasks.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun insert(task: Task){
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun update(task: Task){
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: Task){
        viewModelScope.launch {
            repository.delete(task)
        }
    }

}