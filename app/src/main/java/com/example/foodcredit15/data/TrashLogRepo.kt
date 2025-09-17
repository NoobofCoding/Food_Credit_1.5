package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrashLogRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createTrashLog(log: TrashLogRequest, onResult: (TrashLogResponse?) -> Unit) {
        api.createTrashLog(log).enqueue(object : Callback<TrashLogResponse> {
            override fun onResponse(call: Call<TrashLogResponse>, response: Response<TrashLogResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<TrashLogResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getTrashLogs(onResult: (List<TrashLogResponse>?) -> Unit) {
        api.getTrashLogs().enqueue(object : Callback<List<TrashLogResponse>> {
            override fun onResponse(call: Call<List<TrashLogResponse>>, response: Response<List<TrashLogResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<TrashLogResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun deleteTrashLog(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteTrashLog(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
