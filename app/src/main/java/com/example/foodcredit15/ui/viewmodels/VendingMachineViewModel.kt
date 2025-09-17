package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.VendingMachineRepo
import com.example.foodcredit15.network.VendingMachineRequest
import com.example.foodcredit15.network.VendingMachineResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class VendingMachineUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val machines: List<VendingMachineResponse> = emptyList()
)

class VendingMachineViewModel : ViewModel() {
    private val repo = VendingMachineRepo()
    private val _ui = MutableStateFlow(VendingMachineUiState())
    val ui: StateFlow<VendingMachineUiState> = _ui

    fun loadMachines() {
        _ui.value = VendingMachineUiState(loading = true)
        viewModelScope.launch {
            repo.getMachines { list ->
                _ui.value = if (list != null) VendingMachineUiState(machines = list)
                else VendingMachineUiState(error = "Failed to load machines")
            }
        }
    }

    fun createMachine(machine: VendingMachineRequest) {
        _ui.value = VendingMachineUiState(loading = true)
        viewModelScope.launch {
            repo.createMachine(machine) { response ->
                if (response != null) loadMachines()
                else _ui.value = VendingMachineUiState(error = "Failed to create machine")
            }
        }
    }

    fun deleteMachine(id: Int) {
        viewModelScope.launch {
            repo.deleteMachine(id) { success ->
                if (success) loadMachines()
            }
        }
    }
}
