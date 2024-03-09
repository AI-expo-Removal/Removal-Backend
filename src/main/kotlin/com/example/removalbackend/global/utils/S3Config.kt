package com.example.removalbackend.global.utils

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner


@Configuration
class S3Config {
    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String

    companion object {
        const val videoFolder = "video"
    }

    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
            .region(Region.of(region))
            .build()
    }

    @Bean
    fun s3Presigner(): S3Presigner {
        return S3Presigner.builder()
            .region(Region.of(region))
            .build()
    }

    @Bean
    fun amazonS3(): AmazonS3 {
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .build()
    }
}