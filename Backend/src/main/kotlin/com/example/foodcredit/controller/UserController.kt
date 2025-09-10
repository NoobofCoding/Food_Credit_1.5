package com.example.foodcredit.controller

import com.example.foodcredit.dto.LoginRequest
import com.example.foodcredit.model.User
import com.example.foodcredit.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userRepository.save(user)
        return ResponseEntity.ok(savedUser)
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val user = userRepository.findByEmailAndPassword(request.email, request.password)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(401).body(mapOf("error" to "Invalid email or password"))
        }
    }
}
