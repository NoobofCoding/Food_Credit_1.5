package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import kotlinx.coroutines.*

class TrashLogRepo(
    private val api: ApiService = RetrofitClient.instance.create(ApiService::class.java)
) {
    fun createTrashLog(log: TrashLogRequest, onResult: (TrashLogResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createTrashLog(log)
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun getTrashLogs(onResult: (List<TrashLogResponse>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getTrashLogs()
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun deleteTrashLog(id: Int, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.deleteTrashLog(id)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }
}
