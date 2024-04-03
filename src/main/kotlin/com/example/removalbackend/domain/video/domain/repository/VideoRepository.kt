package com.example.removalbackend.domain.video.domain.repository

import com.example.removalbackend.domain.title.domain.Video
import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository : JpaRepository<Video, Long> {
    fun findByVideoId(videoId: Long): Video
}