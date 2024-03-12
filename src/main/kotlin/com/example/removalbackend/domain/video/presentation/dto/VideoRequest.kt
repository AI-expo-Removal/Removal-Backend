package com.example.removalbackend.domain.video.presentation.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class VideoRequest (
    @field:NotNull(message = "null 허용하지 않습니다.")
    @field:Size(max = 200, message = "200자까지 가능합니다.")
    val imageUrl: String
)