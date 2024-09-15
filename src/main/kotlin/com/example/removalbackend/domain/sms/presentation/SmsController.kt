package com.example.removalbackend.domain.sms.presentation

import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.response.PasswordMatchResponse
import com.example.removalbackend.domain.sms.service.SmsService
import net.nurigo.sdk.message.model.Message
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val smsService: SmsService
) {
    @GetMapping("/{phone-number}/random-number")
    fun getMessageList(
        @PathVariable("phone-number") phoneNumber: String,
        @RequestParam("random-number",  required = true) randomNumber: String
    ): PasswordMatchResponse {
        val isVerify = smsService.verifySmsCertification(phoneNumber, randomNumber)
        return PasswordMatchResponse(isVerify)
    }

    @PostMapping()
    fun sendOne(@RequestBody request : SmsRequest){
        val message = Message(
            from = "발신번호 입력",
            to = "수신번호 입력",
            text = "Removal 전화번호 인증입니다.",
            country = "82"
        )
        val response = smsService.sendSMS(request)
        return response
    }
}