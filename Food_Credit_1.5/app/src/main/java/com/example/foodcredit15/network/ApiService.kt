package com.example.foodcredit15.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Request + Response DTOs
data class UserRequest(
    val name: String? = null,
    val phone: String? = null,
    val gender: String? = null,
    val email: String,
    val password: String
)

data class UserResponse(
    val id: Int,
    val name: String,
    val phone: String,
    val gender: String,
    val email: String,
    val role: String
)

interface ApiService {

    @POST("users/register")
    fun registerUser(@Body user: UserRequest): Call<UserResponse>

    @POST("users/login")
    fun loginUser (@Body loginRequest: UserRequest): Call<UserResponse>
}

data class LoginRequest(
    val email: String,
    val password: String
)