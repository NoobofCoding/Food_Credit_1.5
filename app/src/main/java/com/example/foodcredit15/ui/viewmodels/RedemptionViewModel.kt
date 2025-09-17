package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.RedemptionRepo
import com.example.foodcredit15.network.RedemptionRequest
import com.example.foodcredit15.network.RedemptionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RedemptionUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val redemptions: List<RedemptionResponse> = emptyList()
)

class RedemptionViewModel : ViewModel() {
    private val repo = RedemptionRepo()
    private val _ui = MutableStateFlow(RedemptionUiState())
    val ui: StateFlow<RedemptionUiState> = _ui

    fun loadRedemptions() {
        _ui.value = RedemptionUiState(loading = true)
        viewModelScope.launch {
            repo.getRedemptions { list ->
                _ui.value = if (list != null) RedemptionUiState(redemptions = list)
                else RedemptionUiState(error = "Failed to load redemptions")
            }
        }
    }

    fun createRedemption(redemption: RedemptionRequest) {
        _ui.value = RedemptionUiState(loading = true)
        viewModelScope.launch {
            repo.createRedemption(redemption) { response ->
                if (response != null) loadRedemptions()
                else _ui.value = RedemptionUiState(error = "Failed to create redemption")
            }
        }
    }

    fun deleteRedemption(id: Int) {
        viewModelScope.launch {
            repo.deleteRedemption(id) { success ->
                if (success) loadRedemptions()
            }
        }
    }
}
