package com.example.removalbackend.domain.video.presentation

import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleUpdateRequest
import com.example.removalbackend.domain.video.presentation.dto.response.VideoResponse
import com.example.removalbackend.domain.video.service.VideoService
import com.example.removalbackend.domain.video.service.VideoTitleUpdateService
import com.example.removalbackend.domain.video.service.VideoUploadService
import lombok.RequiredArgsConstructor
import lombok.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import javax.annotation.Resource
import javax.validation.Valid

@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
class VideoController(
    private val videoService: VideoService,
    private val videoUploadService: VideoUploadService,
    private val videoTitleUpdateService: VideoTitleUpdateService
) {
    @PostMapping("/video")
    fun saveFile(@RequestPart("file") multiPartFile: MultipartFile): VideoResponse {
        return videoService.uploadVideo(multiPartFile)
    }

    @PostMapping
    fun saveTitle(@RequestBody request: VideoTitleRequest){
        return videoUploadService.videoTitleService(request)
    }

    @PatchMapping
    fun patchTitle(@RequestBody request: VideoTitleUpdateRequest){
        return videoTitleUpdateService.titleUpdate(request)
    }
}