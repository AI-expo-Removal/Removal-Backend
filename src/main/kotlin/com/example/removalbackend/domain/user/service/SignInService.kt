package com.example.removalbackend.domain.user.service

import com.example.removalbackend.domain.user.exception.IncorrectPasswordException
import com.example.removalbackend.domain.user.facade.UserFacade
import com.example.removalbackend.domain.user.presentation.dto.request.SignInRequest
import com.example.removalbackend.domain.user.presentation.dto.response.TokenResponse
import com.example.removalbackend.global.security.jwt.TokenProvider
import javax.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignInService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {
    @Transactional
    fun execute(request: SignInRequest): TokenResponse{
        val user = userFacade.getByAccountId(request.accountId)
        if(!passwordEncoder.matches(request.password, user.password)){
            throw IncorrectPasswordException
        }
        return tokenProvider.generateToken(user.accountId)
    }
}