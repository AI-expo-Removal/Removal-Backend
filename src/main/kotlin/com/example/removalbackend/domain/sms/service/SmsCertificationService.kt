package com.example.removalbackend.domain.sms.service

import com.example.removalbackend.domain.sms.repository.SmsCertification
import org.springframework.stereotype.Service

@Service
class SmsCertificationService(private val smsCertification: SmsCertification) {
    fun createSmsCertification(phoneNumber: String, certificationNumber: String, limitTimeInSeconds: Long = 300) {
        smsCertification.createSmsCertification(phoneNumber, certificationNumber, limitTimeInSeconds)
    }
}