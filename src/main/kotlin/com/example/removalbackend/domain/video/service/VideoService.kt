package com.example.removalbackend.domain.video.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
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
import org.springframework.web.bind.annotation.RequestBody
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
    private val amazonS3: AmazonS3,
    private val s3Utils: S3Utils,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) {
    @Transactional
    fun uploadVideo(multipartFile: MultipartFile): VideoResponse = VideoResponse(s3Utils.uploadVideo(multipartFile))
}