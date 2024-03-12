package com.example.removalbackend.domain.user.domain



import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@RedisHash(value = "RefreshToken", timeToLive = 60 * 60 * 2)
class RefreshToken(
    @Id
    var id: Long? = null,

    @Indexed
    @get: NotBlank
    var token: String
) {
}