package com.example.removalbackend.domain.title.domain

import com.example.removalbackend.domain.user.domain.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "tbl_title")
class Video(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val videoId: Long? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val title: String,

    val createdAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userId: User
)