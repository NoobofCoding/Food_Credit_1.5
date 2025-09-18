package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import kotlinx.coroutines.*

class ItemRepo(
    private val api: ApiService = RetrofitClient.instance.create(ApiService::class.java)
) {
    fun createItem(item: ItemRequest, onResult: (ItemResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createItem(item)
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun getItems(onResult: (List<ItemResponse>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getItems()
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun updateItem(id: Int, item: ItemRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.updateItem(id, item)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }

    fun deleteItem(id: Int, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.deleteItem(id)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }
}
