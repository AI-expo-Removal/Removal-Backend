package com.example.removalbackend.global.sms.controller

import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.service.MessageService
import org.aspectj.bridge.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


class SmsController(
    private val messageService: MessageService
) {
    @PostMapping("/send-one")
    fun sendOne(): SingleMessageSentResponse? {
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        val message = Message(
            from = "발신번호 입력",
            to = "수신번호 입력",
            text = "한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다."
            // country = "국가번호"
        )
        val response = messageService.sendOne(SingleMessageSendingRequest(message))
        println(response)
        return response
    }
}