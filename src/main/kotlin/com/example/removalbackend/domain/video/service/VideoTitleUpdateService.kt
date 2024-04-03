package com.example.removalbackend.domain.video.service

import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.user.facade.UserFacade
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import com.example.removalbackend.domain.video.exception.IncorrectUserException
import com.example.removalbackend.domain.video.facade.VideoFacade
import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleUpdateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VideoTitleUpdateService(
    private val videoFacade: VideoFacade,
    private val userFacade: UserFacade
) {
    @Transactional
    fun titleUpdate(request: VideoTitleUpdateRequest){
        val video = videoFacade.getById(videoId = 0)
        val user = userFacade.getCurrentUser()
        if (user != video.userId) {
            throw IncorrectUserException
        }
        video.titleUpdate(request.title)
    }
}