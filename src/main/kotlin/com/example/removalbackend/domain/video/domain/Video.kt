package com.example.removalbackend.domain.title.domain

import com.example.removalbackend.domain.user.domain.User
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "tbl_video")
class Video(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val videoId: Long,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val title: String,

    val createdAt: LocalDateTime,

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    val videoUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userId: User
)