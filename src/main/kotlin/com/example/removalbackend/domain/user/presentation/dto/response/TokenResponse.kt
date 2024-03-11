package com.example.removalbackend.domain.user.presentation.dto.response

import com.example.removalbackend.domain.user.domain.RefreshToken

class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)