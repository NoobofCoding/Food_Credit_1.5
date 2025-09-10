package com.example.foodcredit.repository

import com.example.foodcredit.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailAndPassword(email: String, password: String): User?
}
