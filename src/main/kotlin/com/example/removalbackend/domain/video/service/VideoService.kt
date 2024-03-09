package com.example.removalbackend.domain.video.service

import com.example.removalbackend.domain.video.domain.Video
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import com.example.removalbackend.domain.video.presentation.dto.VideoRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VideoService(
    private val videoRepository: VideoRepository
) {
    @Transactional
    fun createVideo(request: VideoRequest){
        val video = videoRepository.save(
            Video(
                null,
                request.imageUrl
            )
        )
    }
}