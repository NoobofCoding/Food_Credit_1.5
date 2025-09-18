package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.RedemptionRequest
import com.example.foodcredit15.network.RedemptionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RedemptionState {
    object Idle : RedemptionState()
    object Loading : RedemptionState()
    data class Success(val redemptions: List<RedemptionResponse>) : RedemptionState()
    data class Error(val message: String) : RedemptionState()
}

class RedemptionViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<RedemptionState>(RedemptionState.Idle)
    val state: StateFlow<RedemptionState> = _state

    fun getRedemptions() {
        viewModelScope.launch {
            _state.value = RedemptionState.Loading
            try {
                val list = api.getRedemptions()
                _state.value = RedemptionState.Success(list)
            } catch (e: Exception) {
                _state.value = RedemptionState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun createRedemption(userId: Int, itemId: Int, quantity: Int) {
        viewModelScope.launch {
            _state.value = RedemptionState.Loading
            try {
                api.createRedemption(RedemptionRequest(userId, itemId, quantity))
                getRedemptions()
            } catch (e: Exception) {
                _state.value = RedemptionState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun deleteRedemption(id: Int) {
        viewModelScope.launch {
            _state.value = RedemptionState.Loading
            try {
                api.deleteRedemption(id)
                getRedemptions()
            } catch (e: Exception) {
                _state.value = RedemptionState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
