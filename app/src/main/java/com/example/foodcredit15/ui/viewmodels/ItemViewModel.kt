package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.ItemRequest
import com.example.foodcredit15.network.ItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ItemState {
    object Idle : ItemState()
    object Loading : ItemState()
    data class Success(val items: List<ItemResponse>) : ItemState()
    data class Error(val message: String) : ItemState()
}

class ItemViewModel(private val api: ApiService) : ViewModel() {

    private val _state = MutableStateFlow<ItemState>(ItemState.Idle)
    val state: StateFlow<ItemState> = _state

    fun getItems() {
        viewModelScope.launch {
            _state.value = ItemState.Loading
            try {
                val response = api.getItems()
                _state.value = ItemState.Success(response)
            } catch (e: Exception) {
                _state.value = ItemState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun addItem(name: String, points: Int, stock: Int) {
        viewModelScope.launch {
            _state.value = ItemState.Loading
            try {
                api.createItem(ItemRequest(name, points, stock))
                getItems()
            } catch (e: Exception) {
                _state.value = ItemState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
