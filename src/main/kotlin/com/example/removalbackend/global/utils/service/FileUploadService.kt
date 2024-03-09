package com.example.removalbackend.global.utils.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class FileUploadService(
    private val amazonS3: AmazonS3,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String
) {
    fun saveFile(multipartFile: MultipartFile): String{
        val originalFilename = UUID.randomUUID().toString()

        val metadata = ObjectMetadata().apply {
            contentLength = multipartFile.size
            contentType = multipartFile.contentType
        }

        amazonS3.putObject(bucket, originalFilename, multipartFile.inputStream, metadata)
        return amazonS3.getUrl(bucket, originalFilename).toString()
    }
}