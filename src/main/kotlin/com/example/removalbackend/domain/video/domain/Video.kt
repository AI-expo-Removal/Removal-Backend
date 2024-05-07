package com.example.removalbackend.domain.title.domain

import com.example.removalbackend.domain.user.domain.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "tbl_video")
class Video(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var videoId: Long,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    var title: String,

    val createdAt: LocalDateTime,

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    var videoUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userId: User
) {
    fun titleUpdate(title: String, videoUrl: String){
        this.title = title
        this.videoUrl = videoUrl
    }
}