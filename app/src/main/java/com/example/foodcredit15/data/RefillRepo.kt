package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import kotlinx.coroutines.*

class RefillRepo(
    private val api: ApiService = RetrofitClient.instance.create(ApiService::class.java)
) {
    fun createRefill(refill: RefillRequest, onResult: (RefillResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createRefill(refill)
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun getRefills(onResult: (List<RefillResponse>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getRefills()
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun updateRefill(id: Int, refill: RefillRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.updateRefill(id, refill)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }

    fun deleteRefill(id: Int, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.deleteRefill(id)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }
}
