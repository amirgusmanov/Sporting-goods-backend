package com.amir.springboot.service

import com.amir.springboot.model.AuthRequest
import com.amir.springboot.model.RegisterRequest
import com.amir.springboot.model.User
import com.amir.springboot.repository.UserRepository
import com.amir.springboot.util.Constants
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): String? {
        val user = User(
            email = request.email,
            userPassword = passwordEncoder.encode(request.password),
        ).also { repository.save(it) }
        return jwtService.generateToken(user)
    }

    fun authenticate(request: AuthRequest): String? {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        val user =
            repository.findByEmail(request.email) ?: throw UsernameNotFoundException(Constants.USER_NOT_FOUND_MSG)
        return jwtService.generateToken(user)
    }
}