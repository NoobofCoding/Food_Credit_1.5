package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createEmployee(employee: EmployeeRequest, onResult: (EmployeeResponse?) -> Unit) {
        api.createEmployee(employee).enqueue(object : Callback<EmployeeResponse> {
            override fun onResponse(call: Call<EmployeeResponse>, response: Response<EmployeeResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<EmployeeResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getEmployees(onResult: (List<EmployeeResponse>?) -> Unit) {
        api.getEmployees().enqueue(object : Callback<List<EmployeeResponse>> {
            override fun onResponse(call: Call<List<EmployeeResponse>>, response: Response<List<EmployeeResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<EmployeeResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateEmployee(id: Int, employee: EmployeeRequest, onResult: (Boolean) -> Unit) {
        api.updateEmployee(id, employee).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteEmployee(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteEmployee(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
