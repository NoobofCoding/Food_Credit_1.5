package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DustbinRepo(
    private val api: ApiService = RetrofitClient.instance.create(ApiService::class.java)
) {

    fun createDustbin(dustbin: DustbinRequest, onResult: (DustbinResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createDustbin(dustbin)
                withContext(Dispatchers.Main) {
                    onResult(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }

    fun getDustbins(onResult: (List<DustbinResponse>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getDustbins()
                withContext(Dispatchers.Main) {
                    onResult(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null)
                }
            }
        }
    }

    fun updateDustbin(id: Int, dustbin: DustbinRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.updateDustbin(id, dustbin)
                withContext(Dispatchers.Main) {
                    onResult(true)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false)
                }
            }
        }
    }

    fun deleteDustbin(id: Int, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.deleteDustbin(id)
                withContext(Dispatchers.Main) {
                    onResult(true)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false)
                }
            }
        }
    }
}
