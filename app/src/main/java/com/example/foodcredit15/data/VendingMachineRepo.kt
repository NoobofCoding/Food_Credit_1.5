package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import kotlinx.coroutines.*

class VendingMachineRepo(
    private val api: ApiService = RetrofitClient.instance.create(ApiService::class.java)
) {
    fun createMachine(machine: VendingMachineRequest, onResult: (VendingMachineResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createMachine(machine)
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun getMachines(onResult: (List<VendingMachineResponse>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getMachines()
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun updateMachine(id: Int, machine: VendingMachineRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.updateMachine(id, machine)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }

    fun deleteMachine(id: Int, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.deleteMachine(id)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }
}
