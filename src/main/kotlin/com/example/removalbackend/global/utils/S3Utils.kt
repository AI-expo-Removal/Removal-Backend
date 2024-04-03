package com.example.removalbackend.global.utils

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.example.removalbackend.global.utils.exception.BadFileExtensionException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.util.UUID

@Component
class S3Utils(
    private val amazonS3: AmazonS3Client,

    @Value("\${cloud.aws.s3.bucket}")
    private val bucketName: String
) {
    fun uploadVideo(file: MultipartFile): String {
        val ext = verificationFile(file)
        val fileName = "${UUID.randomUUID()}.$ext"
        val objMeta = ObjectMetadata()
        val bytes = file.bytes
        val byteArrayIs = ByteArrayInputStream(bytes)
        objMeta.contentLength = bytes.size.toLong()
        amazonS3.putObject(
            PutObjectRequest(bucketName, fileName, byteArrayIs, objMeta)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return amazonS3.getResourceUrl(bucketName, fileName)
    }

    private fun verificationFile(file: MultipartFile): String {
        val originalFilename = file.originalFilename
        val ext = originalFilename!!.substring(originalFilename.lastIndexOf(".") + 1)
        if (!(ext.equals("jpg", ignoreCase = true) ||
                    ext.equals("jpeg", ignoreCase = true) ||
                    ext.equals("png", ignoreCase = true) ||
                    ext.equals("heic", ignoreCase = true) ||
                    ext.equals("mp4", ignoreCase = true) ||
                    ext.equals("mov", ignoreCase = true) ||
                    ext.equals("avi", ignoreCase = true))
        ) {
            throw BadFileExtensionException
        }
        return ext
    }
}