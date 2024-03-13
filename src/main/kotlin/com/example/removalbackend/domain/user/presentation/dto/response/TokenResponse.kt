package com.example.removalbackend.domain.user.presentation.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)