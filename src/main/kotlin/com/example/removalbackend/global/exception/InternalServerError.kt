package com.example.removalbackend.global.exception

import com.example.removalbackend.global.error.exception.ErrorCode
import com.example.removalbackend.global.error.exception.RemovalException

object InternalServerError : RemovalException(ErrorCode.INTERNAL_SERVER_ERROR)