package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DustbinRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createDustbin(dustbin: DustbinRequest, onResult: (DustbinResponse?) -> Unit) {
        api.createDustbin(dustbin).enqueue(object : Callback<DustbinResponse> {
            override fun onResponse(call: Call<DustbinResponse>, response: Response<DustbinResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<DustbinResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getDustbins(onResult: (List<DustbinResponse>?) -> Unit) {
        api.getDustbins().enqueue(object : Callback<List<DustbinResponse>> {
            override fun onResponse(call: Call<List<DustbinResponse>>, response: Response<List<DustbinResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<DustbinResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateDustbin(id: Int, dustbin: DustbinRequest, onResult: (Boolean) -> Unit) {
        api.updateDustbin(id, dustbin).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteDustbin(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteDustbin(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
