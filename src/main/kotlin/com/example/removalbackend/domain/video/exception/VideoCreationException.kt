package com.example.removalbackend.domain.video.exception

import com.example.removalbackend.global.error.exception.ErrorCode
import com.example.removalbackend.global.error.exception.RemovalException

object VideoCreationException : RemovalException(ErrorCode.FAILED_TO_CREATE_VIDEO)