package com.example.removalbackend.domain.video.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class VideoTitleRequest(
    val videoUrl: String,

    @JsonProperty("title")
    val title: String
)