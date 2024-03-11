package com.example.removalbackend.global.security

import com.example.removalbackend.global.security.jwt.TokenProvider
import com.fasterxml.jackson.databind.ObjectMapper

class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val tokenProvider: TokenProvider
) {

}