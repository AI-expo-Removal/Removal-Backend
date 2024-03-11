package com.example.removalbackend.global.exception

abstract class BaseException : RuntimeException() {
    abstract fun getExceptionType(): BaseExceptionType
}