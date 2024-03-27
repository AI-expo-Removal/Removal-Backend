//package com.example.removalbackend.domain.sms.presentation
//
//import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
//import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
//import com.example.removalbackend.domain.sms.service.SmsService
//import net.nurigo.sdk.message.model.Message
//import net.nurigo.sdk.message.request.SingleMessageSendingRequest
//import net.nurigo.sdk.message.response.SingleMessageSentResponse
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/sms")
//class SmsController(
//    private val smsService: SmsService
//) {
//    @GetMapping("/{phone-number}/random-number")
//    fun getMessageList(
//        @PathVariable("phone-number") phoneNumber: String,
//        @RequestParam("code") randomNumber: String
//    ): String {
//        val verifySmsRequest = VerifySmsRequest(phoneNumber, randomNumber)
//        return smsService.verifySms(verifySmsRequest)
//    }
//
//    @PostMapping()
//    fun sendOne(@RequestBody request : SmsRequest){
//        val message = Message(
//            from = "발신번호 입력",
//            to = "수신번호 입력",
//            text = "Removal 전화번호 인증입니다.",
//            country = "82"
//        )
//        val response = smsService.sendSMS(request)
//        println(response)
//        return response
//    }
//}