package com.example.removalbackend.domain.video.service

import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class GetVideoTitleService(
    private val videoRepository: VideoRepository
) {
    fun getTitle(videoId: Long): Video {
        return videoRepository.findById(videoId).orElseThrow { (EntityNotFoundException("동영상을 찾을 수 없음 $videoId")) }
    }
}