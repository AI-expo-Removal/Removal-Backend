package com.example.removalbackend.domain.sms.presentation

import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.service.SmsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val smsService: SmsService
) {

    @PostMapping
    fun sendSms(@RequestBody smsRequest: SmsRequest): String {
        smsService.sendSMS(smsRequest)
        return "SMS 발송 완료"
    }
}