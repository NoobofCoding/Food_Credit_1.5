package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.EmployeeRequest
import com.example.foodcredit15.network.EmployeeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class EmployeeState {
    object Idle : EmployeeState()
    object Loading : EmployeeState()
    data class Success(val employee: EmployeeResponse) : EmployeeState()
    data class Error(val message: String) : EmployeeState()
}

class EmployeeViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<EmployeeState>(EmployeeState.Idle)
    val state: StateFlow<EmployeeState> = _state

    fun createEmployee(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = EmployeeState.Loading
            try {
                val response = api.createEmployee(EmployeeRequest(name, email, password))
                _state.value = EmployeeState.Success(response)
            } catch (e: Exception) {
                _state.value = EmployeeState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun getEmployees() {
        viewModelScope.launch {
            _state.value = EmployeeState.Loading
            try {
                val list = api.getEmployees()
                if (list.isNotEmpty()) {
                    _state.value = EmployeeState.Success(list.first()) // adapt as needed
                } else {
                    _state.value = EmployeeState.Error("No employees found")
                }
            } catch (e: Exception) {
                _state.value = EmployeeState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
