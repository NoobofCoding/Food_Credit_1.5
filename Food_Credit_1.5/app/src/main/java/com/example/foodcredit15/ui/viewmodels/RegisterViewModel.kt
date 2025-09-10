package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.UserResponse
import com.example.foodcredit15.data.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val user: UserResponse? = null
)

class RegisterViewModel : ViewModel() {

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

        viewModelScope.launch {
            UserRepo.registerUser(name, phone, gender, email, password) { user ->
                if (user != null) {
                    _ui.value = RegisterUiState(user = user)
                } else {
                    _ui.value = RegisterUiState(error = "Registration failed")
                }
                onResult(user)
            }
        }
    }
}
