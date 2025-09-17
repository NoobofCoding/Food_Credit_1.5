package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.RefillRepo
import com.example.foodcredit15.network.RefillRequest
import com.example.foodcredit15.network.RefillResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RefillUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val refills: List<RefillResponse> = emptyList()
)

class RefillViewModel : ViewModel() {
    private val repo = RefillRepo()
    private val _ui = MutableStateFlow(RefillUiState())
    val ui: StateFlow<RefillUiState> = _ui

    fun loadRefills() {
        _ui.value = RefillUiState(loading = true)
        viewModelScope.launch {
            repo.getRefills { list ->
                _ui.value = if (list != null) RefillUiState(refills = list)
                else RefillUiState(error = "Failed to load refills")
            }
        }
    }

    fun createRefill(refill: RefillRequest) {
        _ui.value = RefillUiState(loading = true)
        viewModelScope.launch {
            repo.createRefill(refill) { response ->
                if (response != null) loadRefills()
                else _ui.value = RefillUiState(error = "Failed to create refill")
            }
        }
    }

    fun deleteRefill(id: Int) {
        viewModelScope.launch {
            repo.deleteRefill(id) { success ->
                if (success) loadRefills()
            }
        }
    }
}
