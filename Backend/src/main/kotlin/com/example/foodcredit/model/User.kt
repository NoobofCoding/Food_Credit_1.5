package com.example.foodcredit.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val phone: String,
    val gender: String,
    val email: String,
    val password: String,
    val role: String = "USER"
)
