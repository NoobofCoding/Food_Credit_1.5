package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createItem(item: ItemRequest, onResult: (ItemResponse?) -> Unit) {
        api.createItem(item).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getItems(onResult: (List<ItemResponse>?) -> Unit) {
        api.getItems().enqueue(object : Callback<List<ItemResponse>> {
            override fun onResponse(call: Call<List<ItemResponse>>, response: Response<List<ItemResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<ItemResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateItem(id: Int, item: ItemRequest, onResult: (Boolean) -> Unit) {
        api.updateItem(id, item).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteItem(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteItem(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}
