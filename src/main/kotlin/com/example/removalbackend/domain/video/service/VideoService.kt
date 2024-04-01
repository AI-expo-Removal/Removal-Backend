package com.example.removalbackend.domain.video.service

import com.amazonaws.services.s3.AmazonS3
import com.example.removalbackend.domain.title.domain.Video
import com.example.removalbackend.domain.user.domain.User
import com.example.removalbackend.domain.user.domain.repository.UserRepository
import com.example.removalbackend.domain.user.facade.UserFacade
import com.example.removalbackend.domain.video.domain.repository.VideoRepository
import com.example.removalbackend.domain.video.presentation.dto.request.VideoTitleRequest
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDateTime
import java.util.*


@Slf4j
@Service
class VideoService(
    private val videoRepository: VideoRepository,
    private val userFacade: UserFacade
) {
    fun uploadFile(file: MultipartFile, chunkNumber: Int, totalChunks: Int): Boolean {
        // 파일 업로드 위치
        val uploadDir = "video"
        val dir = File(uploadDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        // 임시 저장 파일 이름
        val filename = file.originalFilename + ".part" + chunkNumber
        val filePath: Path = Paths.get(uploadDir, filename)
        // 임시 저장
        Files.write(filePath, file.bytes)

        // 마지막 조각이 전송 됐을 경우
        return if (chunkNumber == totalChunks - 1) {
            val split = file.originalFilename!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val outputFilename = UUID.randomUUID().toString() + "." + split[split.size - 1]
            val outputFile: Path = Paths.get(uploadDir, outputFilename)
            Files.createFile(outputFile)

            // 임시 파일들을 하나로 합침
            for (i in 0 until totalChunks) {
                val chunkFile: Path = Paths.get(uploadDir, file.originalFilename + ".part" + i)
                Files.write(outputFile, Files.readAllBytes(chunkFile), StandardOpenOption.APPEND)
                // 합친 후 삭제
                Files.delete(chunkFile)
            }
            true
        } else {
            false
        }
    }

    @Transactional
    fun saveTitle(request: VideoTitleRequest){
        val user = userFacade.getCurrentUser()
        val videoBox = Video(
            0,
            request.title,
            LocalDateTime.now(),
            userId = user
        )
        videoRepository.save(videoBox)
    }
}