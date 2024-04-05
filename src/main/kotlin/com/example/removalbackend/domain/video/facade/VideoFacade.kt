package com.example.removalbackend.domain.video.facade

import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import com.example.removalbackend.domain.video.exception.VideoNotFoundException
import org.springframework.stereotype.Component

@Component
class VideoFacade(
    private val videoRepository: VideoRepository
) {
    fun getById(videoId: Long): Video {
        return videoRepository.findById(videoId).orElseThrow { VideoNotFoundException }
    }
}