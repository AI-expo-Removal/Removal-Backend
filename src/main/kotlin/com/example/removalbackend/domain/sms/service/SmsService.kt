//package com.example.removalbackend.domain.sms.service
//
//import com.example.removalbackend.domain.sms.exception.CoolSmsException
//import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
//import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
//import com.example.removalbackend.domain.sms.repository.SmsCertification
//import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.JsonElement
//import kotlinx.serialization.json.JsonObject
//import kotlinx.serialization.json.encodeToJsonElement
//import net.nurigo.sdk.message.model.Message
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//import java.util.*
//import kotlin.collections.HashMap
//
//@Service
//class SmsService(
//    private val smsCertification: SmsCertification
//) {
//    @Value("\${SMS_KEY}")
//    private lateinit var apiKey: String
//
//    @Value("\${SMS_SECRET}")
//    private lateinit var apiSecret: String
//
//    @Value("\${SMS_NUMBER}")
//    private lateinit var fromNumber: String
//
//    private class Message(
//        private val apiKey: String,
//        private val apiSecret: String
//    ) {
//        fun send(params: HashMap<String, JsonElement>): JsonObject {
//            // 실제 SMS 전송 로직 구현
//            // 예시로 JsonObject를 반환
//            return JsonObject(mapOf("result" to Json.encodeToJsonElement("success")))
//        }
//    }
//
//    private fun createRandomNumber(): String = List(4) { Random().nextInt(10) }.joinToString("")
//
//    private fun makeParams(to: String, randomNum: String): JsonObject {
//        return JsonObject(
//            mapOf(
//                "from" to Json.encodeToJsonElement(fromNumber),
//                "type" to Json.encodeToJsonElement("SMS"),
//                "app_version" to Json.encodeToJsonElement("test app 1.2"),
//                "to" to Json.encodeToJsonElement(to),
//                "text" to Json.encodeToJsonElement("[Removal] 인증번호: $randomNum")
//            )
//        )
//    }
//
//    // 인증번호 전송하기
//    fun sendSMS(request: SmsRequest) {
//        val coolsms = Message(apiKey, apiSecret)
//        val randomNum = createRandomNumber()
//        println(randomNum)
//
//        val params = makeParams(request.phoneNumber, randomNum)
//        val map = hashMapOf<String, JsonElement>("message" to params)
//
//        try {
//            this.createSmsCertification(request.phoneNumber, randomNum, 300)
//            val obj = coolsms.send(map) // JSON 객체를 전송 로직에 전달
//            println(obj.toString())
//        } catch (e: CoolSmsException) {
//            println("SMS 전송 오류: ${e.message}, 코드: ${e.code}")
//        }
//    }
//
//    fun verifySms(request: VerifySmsRequest): String {
//        // 인증번호를 받지 않았거나 인증번호가 존재하지 않는 경우 예외 처리
//        if (!smsCertification.hasKey(request.phoneNumber)) {
//            throw IllegalArgumentException("인증번호를 받지 않았거나, 인증번호가 존재하지 않습니다.")
//        }
//        // 인증번호가 일치하지 않는 경우 예외 처리
//        if (!isVerify(request)) {
//            throw IllegalArgumentException("인증번호가 일치하지 않습니다.")
//        } else {
//            smsCertification.deleteSmsCertification(request.phoneNumber)
//        }
//        return "인증 완료되었습니다."
//    }
//
//    private fun isVerify(request: VerifySmsRequest): Boolean {
//        val certificationNumber = smsCertification.getSmsCertification(request.phoneNumber)
//        return certificationNumber != null && certificationNumber == request.randomNumber
//    }
//
//    fun createSmsCertification(phoneNumber: String, certificationNumber: String, limitTimeInSeconds: Long = 300) {
//        smsCertification.createSmsCertification(phoneNumber, certificationNumber, limitTimeInSeconds)
//    }
//
//}
