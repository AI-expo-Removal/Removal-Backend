package com.example.removalbackend.domain.video.presentation

import com.example.removalbackend.domain.video.presentation.dto.VideoRequest
import com.example.removalbackend.domain.video.service.VideoService
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/video")
@RestController
class VideoController(
    private val videoService: VideoService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    fun createVideo(@RequestBody @Valid request: VideoRequest){
        videoService.createVideo(request)
    }
}