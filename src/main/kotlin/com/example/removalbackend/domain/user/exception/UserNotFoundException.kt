package com.example.removalbackend.domain.user.exception

import com.example.removalbackend.global.error.exception.ErrorCode
import com.example.removalbackend.global.error.exception.RemovalException

object UserNotFoundException : RemovalException(ErrorCode.USER_NOT_FOUND)