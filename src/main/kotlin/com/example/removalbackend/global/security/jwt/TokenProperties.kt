package com.example.removalbackend.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt", ignoreInvalidFields = false)
data class TokenProperties (
    val secretKey: String,
    val accessExp: Long,
    val refreshExp: Long,
    val header: String,
    val prefix: String
)