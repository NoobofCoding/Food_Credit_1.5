package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.TaskRequest
import com.example.foodcredit15.network.TaskResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TaskState {
    object Idle : TaskState()
    object Loading : TaskState()
    data class Success(val tasks: List<TaskResponse>) : TaskState()
    data class Error(val message: String) : TaskState()
}

class TaskViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<TaskState>(TaskState.Idle)
    val state: StateFlow<TaskState> = _state

    fun getTasks() {
        viewModelScope.launch {
            _state.value = TaskState.Loading
            try {
                val list = api.getTasks()
                _state.value = TaskState.Success(list)
            } catch (e: Exception) {
                _state.value = TaskState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun createTask(employeeId: Int, description: String, status: String) {
        viewModelScope.launch {
            _state.value = TaskState.Loading
            try {
                api.createTask(TaskRequest(employeeId, description, status))
                getTasks()
            } catch (e: Exception) {
                _state.value = TaskState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun updateTask(id: Int, employeeId: Int, description: String, status: String) {
        viewModelScope.launch {
            _state.value = TaskState.Loading
            try {
                api.updateTask(id, TaskRequest(employeeId, description, status))
                getTasks()
            } catch (e: Exception) {
                _state.value = TaskState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            _state.value = TaskState.Loading
            try {
                api.deleteTask(id)
                getTasks()
            } catch (e: Exception) {
                _state.value = TaskState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
