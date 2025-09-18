package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeRepo(
    private val api: ApiService = RetrofitClient.instance.create(ApiService::class.java)
) {
    fun createEmployee(employee: EmployeeRequest, onResult: (EmployeeResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.createEmployee(employee)
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun getEmployees(onResult: (List<EmployeeResponse>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getEmployees()
                withContext(Dispatchers.Main) { onResult(response) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(null) }
            }
        }
    }

    fun updateEmployee(id: Int, employee: EmployeeRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.updateEmployee(id, employee)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }

    fun deleteEmployee(id: Int, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.deleteEmployee(id)
                withContext(Dispatchers.Main) { onResult(true) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onResult(false) }
            }
        }
    }
}
