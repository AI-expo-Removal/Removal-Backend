package com.example.removalbackend.domain.video.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

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