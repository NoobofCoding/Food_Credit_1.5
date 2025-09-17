package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.TrashLogRepo
import com.example.foodcredit15.network.TrashLogRequest
import com.example.foodcredit15.network.TrashLogResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class TrashLogUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val logs: List<TrashLogResponse> = emptyList()
)

class TrashLogViewModel : ViewModel() {
    private val repo = TrashLogRepo()
    private val _ui = MutableStateFlow(TrashLogUiState())
    val ui: StateFlow<TrashLogUiState> = _ui

    fun loadTrashLogs() {
        _ui.value = TrashLogUiState(loading = true)
        viewModelScope.launch {
            repo.getTrashLogs { list ->
                _ui.value = if (list != null) TrashLogUiState(logs = list)
                else TrashLogUiState(error = "Failed to load logs")
            }
        }
    }

    fun createTrashLog(log: TrashLogRequest) {
        _ui.value = TrashLogUiState(loading = true)
        viewModelScope.launch {
            repo.createTrashLog(log) { response ->
                if (response != null) loadTrashLogs()
                else _ui.value = TrashLogUiState(error = "Failed to create log")
            }
        }
    }

    fun deleteTrashLog(id: Int) {
        viewModelScope.launch {
            repo.deleteTrashLog(id) { success ->
                if (success) loadTrashLogs()
            }
        }
    }
}
