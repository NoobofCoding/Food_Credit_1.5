package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.DustbinRequest
import com.example.foodcredit15.network.DustbinResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DustbinState {
    object Idle : DustbinState()
    object Loading : DustbinState()
    data class Success(val dustbins: List<DustbinResponse>) : DustbinState()
    data class Error(val message: String) : DustbinState()
}

class DustbinViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<DustbinState>(DustbinState.Idle)
    val state: StateFlow<DustbinState> = _state

    fun getDustbins() {
        viewModelScope.launch {
            _state.value = DustbinState.Loading
            try {
                val response = api.getDustbins()
                _state.value = DustbinState.Success(response)
            } catch (e: Exception) {
                _state.value = DustbinState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun createDustbin(location: String, capacityKg: Float, status: String) {
        viewModelScope.launch {
            _state.value = DustbinState.Loading
            try {
                val response = api.createDustbin(DustbinRequest(location, capacityKg.toInt(), status))
                getDustbins() // refresh list
            } catch (e: Exception) {
                _state.value = DustbinState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
