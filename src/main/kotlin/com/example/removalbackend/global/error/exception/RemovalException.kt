package com.example.removalbackend.global.error.exception

import com.example.removalbackend.global.error.exception.ErrorCode

abstract class RemovalException(
    val errorCode: ErrorCode,
) : RuntimeException() {
    val status: Int
        get() = errorCode.status
    override val message: String
        get() = errorCode.message
}