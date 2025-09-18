package com.example.foodcredit15.ui.viewmodels

import com.example.foodcredit15.network.UserResponse

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val user: UserResponse) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}
