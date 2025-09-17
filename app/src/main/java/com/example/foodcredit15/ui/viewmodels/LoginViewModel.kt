package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.UserRepo
import com.example.foodcredit15.network.UserRequest
import com.example.foodcredit15.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: UserResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {
    private val repo = UserRepo()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            repo.loginUser(email, password) { user ->
                if (user == null) {
                    _uiState.value = LoginUiState.Error("Invalid email or password")
                    return@loginUser
                }

                // If qrCode missing or blank, generate it and save
                val currentQr = user.qrCode
                if (currentQr.isNullOrBlank()) {
                    val newQr = "QR_${user.userId}_${System.currentTimeMillis()}"

                    // Build a UserRequest to update: use existing fields from user response
                    // NOTE: this assumes UserResponse contains passwordHash. If not, backend must expose a dedicated endpoint to update qr only.
                    val userReq = UserRequest(
                        name = user.name,
                        email = user.email,
                        passwordHash = user.passwordHash,
                        qrCode = newQr
                    )

                    repo.updateUser(user.userId, userReq) { success ->
                        if (success) {
                            // create a new UserResponse with qrCode set (we can't modify original)
                            val updatedUser = user.copy(qrCode = newQr)
                            _uiState.value = LoginUiState.Success(updatedUser)
                        } else {
                            _uiState.value = LoginUiState.Error("Failed to save QR code")
                        }
                    }
                } else {
                    _uiState.value = LoginUiState.Success(user)
                }
            }
        }
    }
}
