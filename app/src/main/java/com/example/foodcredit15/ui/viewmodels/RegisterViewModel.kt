package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.UserRequest
import com.example.foodcredit15.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val api: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun register(
        name: String,
        phone: String,
        gender: String,
        email: String,
        password: String
    ) {
        _uiState.value = RegisterUiState.Loading

        viewModelScope.launch {
            try {
                val request = UserRequest(
                    name = name,
                    email = email,
                    passwordHash = password,
                    qrCode = null
                )
                val response: UserResponse = api.createUser(request)
                _uiState.value = RegisterUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = RegisterUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
