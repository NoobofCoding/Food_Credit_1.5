package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RedemptionRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createRedemption(redemption: RedemptionRequest, onResult: (RedemptionResponse?) -> Unit) {
        api.createRedemption(redemption).enqueue(object : Callback<RedemptionResponse> {
            override fun onResponse(call: Call<RedemptionResponse>, response: Response<RedemptionResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<RedemptionResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getRedemptions(onResult: (List<RedemptionResponse>?) -> Unit) {
        api.getRedemptions().enqueue(object : Callback<List<RedemptionResponse>> {
            override fun onResponse(call: Call<List<RedemptionResponse>>, response: Response<List<RedemptionResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<RedemptionResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun deleteRedemption(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteRedemption(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
