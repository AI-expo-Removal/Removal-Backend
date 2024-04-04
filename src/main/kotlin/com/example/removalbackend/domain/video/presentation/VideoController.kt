package com.example.removalbackend.domain.video.presentation

import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import com.example.removalbackend.domain.video.exception.IncorrectUserException
import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleUpdateRequest
import com.example.removalbackend.domain.video.presentation.dto.response.VideoResponse
import com.example.removalbackend.domain.video.service.GetVideoTitleService
import com.example.removalbackend.domain.video.service.VideoService
import com.example.removalbackend.domain.video.service.VideoTitleUpdateService
import com.example.removalbackend.domain.video.service.VideoTitleUploadService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
class VideoController(
    private val videoService: VideoService,
    private val videoUploadService: VideoTitleUploadService,
    private val videoTitleUpdateService: VideoTitleUpdateService,
    private val videoRepository: VideoRepository,
    private val getVideoTitleService: GetVideoTitleService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/video")
    fun saveFile(@RequestPart("file") multiPartFile: MultipartFile): VideoResponse {
        return videoService.uploadVideo(multiPartFile)
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun saveTitle(@RequestBody request: VideoTitleRequest) {
        return videoUploadService.videoTitleService(request)
    }
    @PatchMapping
    fun updateVideoTitle(
        @RequestBody request: VideoTitleUpdateRequest,
        @RequestHeader("user-id") userId: String
    ): ResponseEntity<String> {
        try {
            videoTitleUpdateService.titleUpdate(request)
            return ResponseEntity.ok("동영상 제목이 성공적으로 수정되었습니다.")
        } catch (e: IncorrectUserException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("사용자는 이 비디오의 소유자가 아닙니다.")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("동영상 제목을 수정하는 도중 오류가 발생했습니다.")
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{video-id}")
    fun getTitle(@PathVariable("video-id") videoId: Long): ResponseEntity<Video>{
        val title = getVideoTitleService.getTitle(videoId)
        return ResponseEntity.ok(title)
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{video-id}")
    fun deleteVideoAndTitle(@PathVariable("video-id") videoId: Long){
        videoRepository.deleteById(videoId)
    }
}