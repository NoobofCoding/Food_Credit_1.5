package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RefillRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createRefill(refill: RefillRequest, onResult: (RefillResponse?) -> Unit) {
        api.createRefill(refill).enqueue(object : Callback<RefillResponse> {
            override fun onResponse(call: Call<RefillResponse>, response: Response<RefillResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<RefillResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getRefills(onResult: (List<RefillResponse>?) -> Unit) {
        api.getRefills().enqueue(object : Callback<List<RefillResponse>> {
            override fun onResponse(call: Call<List<RefillResponse>>, response: Response<List<RefillResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<RefillResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateRefill(id: Int, refill: RefillRequest, onResult: (Boolean) -> Unit) {
        api.updateRefill(id, refill).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteRefill(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteRefill(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
