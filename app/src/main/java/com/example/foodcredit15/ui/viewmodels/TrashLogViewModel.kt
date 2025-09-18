package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.TrashLogRequest
import com.example.foodcredit15.network.TrashLogResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TrashLogState {
    object Idle : TrashLogState()
    object Loading : TrashLogState()
    data class Success(val logs: List<TrashLogResponse>) : TrashLogState()
    data class Error(val message: String) : TrashLogState()
}

class TrashLogViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<TrashLogState>(TrashLogState.Idle)
    val state: StateFlow<TrashLogState> = _state

    fun getTrashLogs() {
        viewModelScope.launch {
            _state.value = TrashLogState.Loading
            try {
                val response = api.getTrashLogs()
                _state.value = TrashLogState.Success(response)
            } catch (e: Exception) {
                _state.value = TrashLogState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun addTrashLog(userId: Int, dustbinId: Int, weight: Float, trashType: String) {
        viewModelScope.launch {
            _state.value = TrashLogState.Loading
            try {
                api.createTrashLog(TrashLogRequest(userId, dustbinId, weight.toInt(), trashType))
                getTrashLogs() // refresh
            } catch (e: Exception) {
                _state.value = TrashLogState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
