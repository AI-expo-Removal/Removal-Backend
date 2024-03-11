package com.example.removalbackend.global.exception

import com.example.removalbackend.global.error.exception.ErrorCode
import com.example.removalbackend.global.error.exception.RemovalException

object TokenExpiredException : RemovalException(ErrorCode.TOKEN_EXPIRED)