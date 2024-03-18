package com.example.removalbackend.domain.sms.presentation

import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.response.SmsResponse
import com.example.removalbackend.domain.sms.service.SmsService
import com.example.removalbackend.domain.user.presentation.dto.request.SignUpRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val smsService: SmsService
) {
    @PostMapping
    fun sendSms(@RequestBody smsRequest: SmsRequest): SmsResponse{
        return smsService.sendSMS(smsRequest)
    }

    @GetMapping
    fun verifySms(@RequestBody verifySmsRequest: VerifySmsRequest): String{
        return smsService.verifySms(verifySmsRequest)
    }
}