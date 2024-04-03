package com.example.removalbackend.domain.video.domain.repository

import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VideoRepository : JpaRepository<Video, Long> {
    fun findByVideoId(videoId: Long): Video
}