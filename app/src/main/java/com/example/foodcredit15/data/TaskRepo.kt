package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createTask(task: TaskRequest, onResult: (TaskResponse?) -> Unit) {
        api.createTask(task).enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getTasks(onResult: (List<TaskResponse>?) -> Unit) {
        api.getTasks().enqueue(object : Callback<List<TaskResponse>> {
            override fun onResponse(call: Call<List<TaskResponse>>, response: Response<List<TaskResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateTask(id: Int, task: TaskRequest, onResult: (Boolean) -> Unit) {
        api.updateTask(id, task).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteTask(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteTask(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
