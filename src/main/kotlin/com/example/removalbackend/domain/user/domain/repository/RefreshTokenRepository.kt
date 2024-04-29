package com.example.removalbackend.domain.user.domain.repository

import com.example.removalbackend.domain.user.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
}