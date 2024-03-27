package com.example.removalbackend.domain.sms.presentation

import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
import com.example.removalbackend.domain.sms.service.SmsService
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.model.MessageStatusType
import net.nurigo.sdk.message.request.MessageListRequest
import net.nurigo.sdk.message.response.MessageListResponse
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val smsService: SmsService
) {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    fun getMessageList(@RequestBody verifySmsRequest: VerifySmsRequest): String {
        val request = MessageListRequest()

        // 검색할 건 수, 값 미지정 시 20건 조회, 최대 500건 까지 설정 가능
        request.limit = 1

        // 조회 후 다음 페이지로 넘어가려면 조회 당시 마지막의 messageId를 입력해야 한다
        request.startKey = "메시지 ID"

        request.to = "검색할 수신번호"
        request.from = "검색할 발신번호"

        // 메시지 상태 검색, PENDING은 대기 건, SENDING은 발송 중,COMPLETE는 발송완료, FAILED는 발송에 실패한 모든 건입니다.

        request.status = MessageStatusType.PENDING
        request.status = MessageStatusType.SENDING
        request.status = MessageStatusType.COMPLETE
        request.status = MessageStatusType.FAILED


        request.messageId = "검색할 메시지 ID"

        // 검색할 메시지 목록
        val messageIds = mutableListOf<String>()
        messageIds.add("검색할 메시지 ID");
        request.messageIds = messageIds;

        val response = smsService.verifySms(verifySmsRequest)
        println(response)

        return response
    }

    //    단일 메시지 발송 예제
    @PostMapping()
    fun sendOne(@RequestBody request: SmsRequest) {
        val message = Message(
            from = "01091920087",
            to = "01091920087",
            text = "[Removal] 인증번호 입니다.",
            country = "82"
        )
        val response = smsService.sendSMS(request)
        println(response)
        return response
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping()
//    fun sendSms(@RequestBody smsRequest: SmsRequest) {
//        return smsService.sendSMS(smsRequest)
//    }

//    @GetMapping()
//    fun verifySms(@RequestBody request: VerifySmsRequest): String {
//        return smsService.verifySms(request)
//
//    }
}