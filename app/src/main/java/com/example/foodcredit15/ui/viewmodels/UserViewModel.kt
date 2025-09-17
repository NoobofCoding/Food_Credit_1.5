package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.UserRepo
import com.example.foodcredit15.network.UserRequest
import com.example.foodcredit15.network.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val users: List<UserResponse> = emptyList()
)

class UserViewModel : ViewModel() {
    private val repo = UserRepo()

    private val _ui = MutableStateFlow(UserUiState())
    val ui: StateFlow<UserUiState> = _ui

    fun loadUsers() {
        _ui.value = UserUiState(loading = true)
        viewModelScope.launch {
            repo.getUsers { list ->
                _ui.value = if (list != null) {
                    UserUiState(users = list)
                } else {
                    UserUiState(error = "Failed to load users")
                }
            }
        }
    }

    fun createUser(user: UserRequest) {
        _ui.value = UserUiState(loading = true)
        viewModelScope.launch {
            repo.createUser(user) { response ->
                if (response != null) {
                    loadUsers() // refresh list
                } else {
                    _ui.value = UserUiState(error = "User creation failed")
                }
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            repo.deleteUser(id) { success ->
                if (success) loadUsers()
            }
        }
    }
}
