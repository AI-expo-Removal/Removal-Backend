package com.example.removalbackend.domain.sms.presentation

import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.response.SmsResponse
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
    fun sendSms(@RequestBody smsRequest: SmsRequest): SmsResponse{
        return smsService.sendSMS(smsRequest)
    }

    @GetMapping()
    fun verifySms(@RequestBody request: VerifySmsRequest): ResponseEntity<Any>{
        return try {
            val message = smsService.verifySms(request)
            ResponseEntity.ok().body(mapOf("message" to message))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }
}