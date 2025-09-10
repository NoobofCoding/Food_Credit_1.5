package com.example.foodcredit15.data

import com.example.foodcredit15.network.ApiService
import com.example.foodcredit15.network.RetrofitClient
import com.example.foodcredit15.network.UserRequest
import com.example.foodcredit15.network.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepo {

    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun registerUser(
        name: String,
        phone: String,
        gender: String,
        email: String,
        password: String,
        onResult: (UserResponse?) -> Unit
    ) {
        val request = UserRequest(name, phone, gender, email, password)
        api.registerUser(request).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                onResult(null)
            }
        })
    }

    fun loginUser(
        email: String,
        password: String,
        onResult: (UserResponse?) -> Unit
    ) {
        val request = UserRequest(email = email, password = password)
        api.loginUser(request).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                onResult(null)
            }
        })
    }

}