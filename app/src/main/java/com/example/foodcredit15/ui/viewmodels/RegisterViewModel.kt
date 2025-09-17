package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.UserRepo
import com.example.foodcredit15.network.UserRequest
import com.example.foodcredit15.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val user: UserResponse? = null
)

class RegisterViewModel : ViewModel() {
    private val repo = UserRepo()

    private val _ui = MutableStateFlow(RegisterUiState())
    val ui: StateFlow<RegisterUiState> = _ui

    fun register(
        name: String,
        phone: String,
        gender: String,
        email: String,
        password: String,
        onResult: (UserResponse?) -> Unit
    ) {
        _ui.value = RegisterUiState(loading = true)

        // generate unique QR string
        val qrCode = "QR_${phone}_${System.currentTimeMillis()}"

        val userReq = UserRequest(
            name = name,
            email = email,
            passwordHash = password,
            qrCode = qrCode
        )

        viewModelScope.launch {
            repo.createUser(userReq) { response ->
                if (response != null) {
                    _ui.value = RegisterUiState(user = response)
                    onResult(response)
                } else {
                    _ui.value = RegisterUiState(error = "Registration failed")
                    onResult(null)
                }
            }
        }
    }
}
