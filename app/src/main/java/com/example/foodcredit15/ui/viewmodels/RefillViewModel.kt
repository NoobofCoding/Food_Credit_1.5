package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.RefillRequest
import com.example.foodcredit15.network.RefillResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RefillState {
    object Idle : RefillState()
    object Loading : RefillState()
    data class Success(val refills: List<RefillResponse>) : RefillState()
    data class Error(val message: String) : RefillState()
}

class RefillViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<RefillState>(RefillState.Idle)
    val state: StateFlow<RefillState> = _state

    fun getRefills() {
        viewModelScope.launch {
            _state.value = RefillState.Loading
            try {
                val list = api.getRefills()
                _state.value = RefillState.Success(list)
            } catch (e: Exception) {
                _state.value = RefillState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun createRefill(employeeId: Int, machineId: Int, itemId: Int, quantity: Int) {
        viewModelScope.launch {
            _state.value = RefillState.Loading
            try {
                api.createRefill(RefillRequest(machineId, itemId, quantity))
                getRefills()
            } catch (e: Exception) {
                _state.value = RefillState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun updateRefill(id: Int, machineId: Int, itemId: Int, quantity: Int) {
        viewModelScope.launch {
            _state.value = RefillState.Loading
            try {
                api.updateRefill(id, RefillRequest(machineId, itemId, quantity))
                getRefills()
            } catch (e: Exception) {
                _state.value = RefillState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun deleteRefill(id: Int) {
        viewModelScope.launch {
            _state.value = RefillState.Loading
            try {
                api.deleteRefill(id)
                getRefills()
            } catch (e: Exception) {
                _state.value = RefillState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
