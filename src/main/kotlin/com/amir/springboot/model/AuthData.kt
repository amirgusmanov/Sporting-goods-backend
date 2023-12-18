package com.amir.springboot.model

import lombok.NoArgsConstructor

@NoArgsConstructor
data class AuthRequest(
    val email: String,
    val password: String
)

data class AuthResponse(val token: String)