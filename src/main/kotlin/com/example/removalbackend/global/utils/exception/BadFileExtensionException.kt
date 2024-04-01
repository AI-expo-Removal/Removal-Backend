package com.example.removalbackend.global.utils.exception

import com.example.removalbackend.global.error.exception.ErrorCode
import com.example.removalbackend.global.error.exception.RemovalException

object BadFileExtensionException : RemovalException(ErrorCode.BAD_FILE_EXTENSION)