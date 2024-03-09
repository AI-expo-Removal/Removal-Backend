package com.example.removalbackend.global.utils.controller

import com.example.removalbackend.global.utils.dto.FileUploadResponse
import com.example.removalbackend.global.utils.service.FileUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

class FileUploadController(
    private val fileUploadService: FileUploadService
) {
    @PostMapping("/video")
    fun saveFile(@RequestParam("file") multipartFile: MultipartFile): FileUploadResponse{
        val fileUrl = fileUploadService.saveFile(multipartFile)
        return FileUploadResponse(fileUrl)
    }
}