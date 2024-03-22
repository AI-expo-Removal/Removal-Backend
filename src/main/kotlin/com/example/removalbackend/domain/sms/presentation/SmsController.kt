package com.example.removalbackend.domain.sms.presentation

import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
import com.example.removalbackend.domain.sms.service.SmsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val smsService: SmsService
) {
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    fun sendSms(@RequestBody smsRequest: SmsRequest) {
        return smsService.sendSMS(smsRequest)
    }

    @GetMapping()
    fun verifySms(@RequestBody request: VerifySmsRequest): String {
        return smsService.verifySms(request)

    }
}