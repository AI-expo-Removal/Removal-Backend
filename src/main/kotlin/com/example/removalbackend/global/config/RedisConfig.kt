package com.example.removalbackend.global.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties,
    private val redisConnectionFactory: RedisConnectionFactory
) {

    // lettuce
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

//    @Bean
//    fun redisTemplate(): RedisTemplate<String, Any> {
//        return RedisTemplate<String, Any>().apply {
//            this.connectionFactory = redisConnectionFactory
//            this.keySerializer = StringRedisSerializer()
//            this.valueSerializer = StringRedisSerializer()
//            this.afterPropertiesSet()
//        }
//    }
}
