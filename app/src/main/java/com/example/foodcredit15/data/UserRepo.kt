package com.example.foodcredit15.data

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
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
            SupabaseHolder.client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
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
