package com.example.removalbackend.domain.video.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "tbl_video")
class Video(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "video_url", columnDefinition = "VARCHAR(200)", nullable = false)
    var videoUrl: String
) {
    protected constructor() : this(videoUrl = "")
}