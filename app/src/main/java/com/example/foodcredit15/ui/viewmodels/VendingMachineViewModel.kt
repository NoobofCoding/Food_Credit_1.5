package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.VendingMachineRequest
import com.example.foodcredit15.network.VendingMachineResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class VendingMachineState {
    object Idle : VendingMachineState()
    object Loading : VendingMachineState()
    data class Success(val machines: List<VendingMachineResponse>) : VendingMachineState()
    data class Error(val message: String) : VendingMachineState()
}

class VendingMachineViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<VendingMachineState>(VendingMachineState.Idle)
    val state: StateFlow<VendingMachineState> = _state

    fun getMachines() {
        viewModelScope.launch {
            _state.value = VendingMachineState.Loading
            try {
                val response = api.getMachines()
                _state.value = VendingMachineState.Success(response)
            } catch (e: Exception) {
                _state.value = VendingMachineState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun addMachine(location: String, status: String) {
        viewModelScope.launch {
            _state.value = VendingMachineState.Loading
            try {
                api.createMachine(VendingMachineRequest(location, status))
                getMachines()
            } catch (e: Exception) {
                _state.value = VendingMachineState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
