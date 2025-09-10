package com.example.foodcredit.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Disable CSRF for APIs
            .authorizeHttpRequests {
                it.requestMatchers("/api/users/register").permitAll() // Allow registration without login
                it.anyRequest().authenticated() // Other endpoints need auth
            }
            .httpBasic { } // Keep basic auth for now

        return http.build()
    }
}
