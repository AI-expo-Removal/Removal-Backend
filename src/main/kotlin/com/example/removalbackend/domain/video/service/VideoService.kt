package com.example.removalbackend.domain.video.service

import com.amazonaws.services.s3.AmazonS3
import com.example.removalbackend.domain.video.presentation.dto.response.VideoResponse
import com.example.removalbackend.global.utils.S3Utils
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

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