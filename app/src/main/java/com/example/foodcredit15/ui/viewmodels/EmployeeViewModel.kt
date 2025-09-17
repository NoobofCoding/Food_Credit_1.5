package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.EmployeeRepo
import com.example.foodcredit15.network.EmployeeRequest
import com.example.foodcredit15.network.EmployeeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class EmployeeUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val employees: List<EmployeeResponse> = emptyList()
)

class EmployeeViewModel : ViewModel() {
    private val repo = EmployeeRepo()
    private val _ui = MutableStateFlow(EmployeeUiState())
    val ui: StateFlow<EmployeeUiState> = _ui

    fun loadEmployees() {
        _ui.value = EmployeeUiState(loading = true)
        viewModelScope.launch {
            repo.getEmployees { list ->
                _ui.value = if (list != null) EmployeeUiState(employees = list)
                else EmployeeUiState(error = "Failed to load employees")
            }
        }
    }

    fun createEmployee(emp: EmployeeRequest) {
        _ui.value = EmployeeUiState(loading = true)
        viewModelScope.launch {
            repo.createEmployee(emp) { response ->
                if (response != null) loadEmployees()
                else _ui.value = EmployeeUiState(error = "Employee creation failed")
            }
        }
    }
}
