package com.example.removalbackend.domain.video.service

import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.user.facade.UserFacade
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import com.example.removalbackend.domain.video.exception.VideoCreationException
import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
import com.example.removalbackend.domain.video.presentation.dto.response.VideoResponse
import com.example.removalbackend.global.utils.S3Utils
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.sql.SQLException
import java.time.LocalDateTime
import java.util.*
import javax.annotation.Resource


@Slf4j
@Service
class VideoService(
    private val videoRepository: VideoRepository,
    private val userFacade: UserFacade,
    private val s3Utils: S3Utils,
) {
    @Value("\${file.upload-dir}")
    private lateinit var uploadDir: String
//    fun uploadFile(file: MultipartFile): String {
//        val fileName = file.originalFilename ?: throw IllegalArgumentException("Invalid file name")
//        val filePath = Paths.get(uploadDir).resolve(fileName)
//        Files.copy(file.inputStream, filePath)
//        return fileName
//    }

//    @Throws(IOException::class)
//    fun downloadFile(fileName: String): ResponseEntity<Resource> {
//        val filePath: Path = Paths.get(uploadDir).resolve(fileName)
//        val bytes: ByteArray = Files.readAllBytes(filePath)
//        val resource: Resource = ByteArrayResource(bytes)
//        return ResponseEntity.ok()
//            .contentType(MediaType.valueOf("video/mp4"))
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$fileName\"")
//            .body(resource)
//    }

    // 비디오 등록
//    @Transactional
//    fun createVideo(request: VideoTitleRequest, userId: Long): Video {
//        try {
//            val video = Video(, userId)
//            return videoRepository.save(video)
//        } catch (ex: DataAccessException) {
//            throw VideoCreationException
//        }
//    }

    @Transactional
    fun saveTitle(request: VideoTitleRequest, file: MultipartFile): VideoResponse {
        val user = userFacade.getCurrentUser()
        val videoBox = Video(
            0,
            request.title,
            LocalDateTime.now(),
            videoUrl = s3Utils.upload(file),
            userId = user
        )
        videoRepository.save(videoBox)

        return VideoResponse(videoBox.videoUrl)
    }
}