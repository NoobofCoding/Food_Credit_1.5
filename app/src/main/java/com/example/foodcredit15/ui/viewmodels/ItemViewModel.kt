package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.ItemRepo
import com.example.foodcredit15.network.ItemRequest
import com.example.foodcredit15.network.ItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ItemUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val items: List<ItemResponse> = emptyList()
)

class ItemViewModel : ViewModel() {
    private val repo = ItemRepo()
    private val _ui = MutableStateFlow(ItemUiState())
    val ui: StateFlow<ItemUiState> = _ui

    fun loadItems() {
        _ui.value = ItemUiState(loading = true)
        viewModelScope.launch {
            repo.getItems { list ->
                _ui.value = if (list != null) ItemUiState(items = list)
                else ItemUiState(error = "Failed to load items")
            }
        }
    }

    fun createItem(item: ItemRequest) {
        _ui.value = ItemUiState(loading = true)
        viewModelScope.launch {
            repo.createItem(item) { response ->
                if (response != null) loadItems()
                else _ui.value = ItemUiState(error = "Failed to create item")
            }
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            repo.deleteItem(id) { success ->
                if (success) loadItems()
            }
        }
    }
}
