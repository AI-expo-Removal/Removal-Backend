package com.example.removalbackend.domain.user.service

import com.example.removalbackend.domain.user.domain.User
import com.example.removalbackend.domain.user.domain.repository.UserRepository
import com.example.removalbackend.domain.user.presentation.dto.request.SignUpRequest
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun execute(request: SignUpRequest){
        val user = User(
            request.accountId,
            passwordEncoder.encode(request.password),
            request.name,
            request.phoneNumber
        )
        userRepository.save(user)
    }
    fun checkUser(accountId: String):Boolean{
        return userRepository.existsByAccountId(accountId)
    }
}