package com.example.removalbackend

import com.example.removalbackend.global.security.jwt.TokenProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
@EnableConfigurationProperties(TokenProperties::class)
@SpringBootApplication
class RemovalBackendApplication
fun main(args: Array<String>) {
	runApplication<RemovalBackendApplication>(*args)
}
