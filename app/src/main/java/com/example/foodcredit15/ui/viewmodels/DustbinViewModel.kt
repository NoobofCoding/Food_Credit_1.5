package com.example.foodcredit15.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodcredit15.data.DustbinRepo
import com.example.foodcredit15.network.DustbinRequest
import com.example.foodcredit15.network.DustbinResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DustbinUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val dustbins: List<DustbinResponse> = emptyList()
)

class DustbinViewModel : ViewModel() {
    private val repo = DustbinRepo()
    private val _ui = MutableStateFlow(DustbinUiState())
    val ui: StateFlow<DustbinUiState> = _ui

    fun loadDustbins() {
        _ui.value = DustbinUiState(loading = true)
        viewModelScope.launch {
            repo.getDustbins { list ->
                _ui.value = if (list != null) DustbinUiState(dustbins = list)
                else DustbinUiState(error = "Failed to load dustbins")
            }
        }
    }

    fun createDustbin(bin: DustbinRequest) {
        _ui.value = DustbinUiState(loading = true)
        viewModelScope.launch {
            repo.createDustbin(bin) { response ->
                if (response != null) loadDustbins()
                else _ui.value = DustbinUiState(error = "Dustbin creation failed")
            }
        }
    }
}
