package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.TaskRepo
import com.example.foodcredit15.network.TaskRequest
import com.example.foodcredit15.network.TaskResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class TaskUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val tasks: List<TaskResponse> = emptyList()
)

class TaskViewModel : ViewModel() {
    private val repo = TaskRepo()
    private val _ui = MutableStateFlow(TaskUiState())
    val ui: StateFlow<TaskUiState> = _ui

    fun loadTasks() {
        _ui.value = TaskUiState(loading = true)
        viewModelScope.launch {
            repo.getTasks { list ->
                _ui.value = if (list != null) TaskUiState(tasks = list)
                else TaskUiState(error = "Failed to load tasks")
            }
        }
    }

    fun createTask(task: TaskRequest) {
        _ui.value = TaskUiState(loading = true)
        viewModelScope.launch {
            repo.createTask(task) { response ->
                if (response != null) loadTasks()
                else _ui.value = TaskUiState(error = "Failed to create task")
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            repo.deleteTask(id) { success ->
                if (success) loadTasks()
            }
        }
    }
}
