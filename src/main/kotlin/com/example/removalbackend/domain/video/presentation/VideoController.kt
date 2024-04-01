package com.example.removalbackend.domain.video.presentation

import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
import com.example.removalbackend.domain.video.presentation.dto.response.VideoResponse
import com.example.removalbackend.domain.video.service.VideoService
import lombok.RequiredArgsConstructor
import lombok.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import javax.annotation.Resource
import javax.validation.Valid

@RequestMapping("/video")
@Controller
@RequiredArgsConstructor
class VideoController(
    private val videoService: VideoService,
) {
//    @PostMapping()
//    fun uploadVideoFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
//        val fileName = videoService.uploadFile(file)
//        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//            .path("/videos/")
//            .path(fileName)
//            .toUriString()
//        return ResponseEntity.ok().body(fileDownloadUri)
//    }

    @PostMapping("/title")
    fun saveTitle(@RequestBody @Valid request : VideoTitleRequest, @RequestParam("file") file: MultipartFile): VideoResponse{
        videoService.saveTitle(request,file)
        return VideoResponse(request.videoUrl)
    }
}