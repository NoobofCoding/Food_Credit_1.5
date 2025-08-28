package com.example.foodcredit15.data

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.Serializable

object UserRepo {

    suspend fun registerUser(
        name: String,
        phone: String,
        gender: String,
        email: String,
        password: String
    ): Boolean {
        return try {
            // Sign up user with email and password
            SupabaseHolder.client.auth.signUpWith {
                email(email)
                password(password)
            }

            // Add extra user details to 'users' table
            SupabaseHolder.client.from("users").insert(
                UserDTO(name, phone, gender, email)
            )

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @Serializable
    data class UserDTO(
        val name: String,
        val phone: String,
        val gender: String,
        val email: String
    )
}
