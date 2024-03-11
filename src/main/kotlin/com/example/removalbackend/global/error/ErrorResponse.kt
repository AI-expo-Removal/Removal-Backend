package com.example.removalbackend.global.error

import com.example.removalbackend.global.error.exception.RemovalException

class ErrorResponse(
    val status: Int,
    val message: String,
) {
    companion object {
        fun of(e: RemovalException): ErrorResponse {
            return ErrorResponse(e.status, e.message)
        }
    }
}