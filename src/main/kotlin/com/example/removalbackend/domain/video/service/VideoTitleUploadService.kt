//package com.example.removalbackend.domain.video.service
//
//import com.example.removalbackend.domain.title.domain.Video
//import com.example.removalbackend.domain.user.facade.UserFacade
//import com.example.removalbackend.domain.video.domain.repository.VideoRepository
//import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
//import org.springframework.stereotype.Service
//import java.time.LocalDateTime
//
//@Service
//class VideoTitleUploadService(
//    private val userFacade: UserFacade,
//    private val videoRepository: VideoRepository
//) {
//    fun videoTitleService(request: VideoTitleRequest) {
//        val user = userFacade.getCurrentUser()
//        val video = Video(0, request.title, LocalDateTime.now(), request.videoUrl, user)
//        videoRepository.save(video)
//    }
//}