package com.example.foodcredit15.data

import com.example.foodcredit15.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo {
    private val api = RetrofitClient.instance.create(ApiService::class.java)

    fun createUser(user: UserRequest, onResult: (UserResponse?) -> Unit) {
        api.createUser(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getUsers(onResult: (List<UserResponse>?) -> Unit) {
        api.getUsers().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun updateUser(id: Int, user: UserRequest, onResult: (Boolean) -> Unit) {
        api.updateUser(id, user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun deleteUser(id: Int, onResult: (Boolean) -> Unit) {
        api.deleteUser(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun loginUser(email: String, password: String, onResult: (UserResponse?) -> Unit) {
        api.loginUser(LoginRequest(email, password)).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) onResult(response.body()) else onResult(null)
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

}
