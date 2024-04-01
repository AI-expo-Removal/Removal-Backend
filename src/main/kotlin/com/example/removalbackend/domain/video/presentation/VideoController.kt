package com.example.removalbackend.domain.video.presentation

import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
import com.example.removalbackend.domain.video.service.VideoService
import lombok.RequiredArgsConstructor
import lombok.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import javax.validation.Valid

@RequestMapping("/video")
@Controller
@RequiredArgsConstructor
class VideoController(
    private val videoService: VideoService,
) {
    @GetMapping()
    fun chunkUploadPage(): String {
        return "chunk"
    }

    @ResponseBody
    @PostMapping()
    @Throws(IOException::class)
    fun chunkUpload(
        @RequestParam("chunk") file: MultipartFile?,
        @RequestParam("chunkNumber") chunkNumber: Int,
        @RequestParam("totalChunks") totalChunks: Int
    ): ResponseEntity<String> {
        val isDone = videoService!!.uploadFile(file!!, chunkNumber, totalChunks)
        return if (isDone) ResponseEntity.ok<String>("File uploaded successfully") else ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .build<String>()
    }

    @PostMapping("/title")
    fun saveTitle(@RequestBody @Valid request : VideoTitleRequest){
        videoService.saveTitle(request)
    }
}