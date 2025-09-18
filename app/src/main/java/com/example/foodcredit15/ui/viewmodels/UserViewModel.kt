package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.UserRequest
import com.example.foodcredit15.network.LoginRequest
import com.example.foodcredit15.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: UserResponse) : UserState()
    data class Error(val message: String) : UserState()
}

class UserViewModel(private val api: ApiService) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    fun registerUser(name: String, email: String, password: String, qrCode: String?) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = api.createUser(UserRequest(name, email, password, qrCode))
                _userState.value = UserState.Success(response)
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = api.loginUser(LoginRequest(email, password))
                _userState.value = UserState.Success(response)
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun getUser(id: Int) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val response = api.getUser(id)
                _userState.value = UserState.Success(response)
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
