package com.example.removalbackend.domain.video.exception

import com.example.removalbackend.global.error.exception.ErrorCode
import com.example.removalbackend.global.error.exception.RemovalException

object VideoNotFoundException : RemovalException(ErrorCode.VIDEO_NOT_FOUND)