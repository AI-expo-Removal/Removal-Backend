package com.example.removalbackend.domain.sms.service

import com.example.removalbackend.domain.sms.exception.CoolSmsException
import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.request.VerifySmsRequest
import com.example.removalbackend.domain.sms.presentation.dto.response.SmsResponse
import com.example.removalbackend.domain.user.presentation.dto.request.SignUpRequest
import com.example.removalbackend.domain.sms.repository.SmsCertification
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class SmsService(
    private val smsCertification: SmsCertification
) {
    @Value("\${SMS_KEY}")
    private lateinit var apiKey: String

    @Value("\${SMS_SECRET}")
    private lateinit var apiSecret: String

    @Value("\${SMS_NUMBER}")
    private lateinit var fromNumber: String

    private class Message(
        private val apiKey: String,
        private val apiSecret: String
    ) {
        fun send(params: HashMap<String, JsonElement>): Any {
            // 실제 전송 로직 작성
            return JsonObject(params) // 임시로 빈 JSON 객체 반환
        }
    }

    private fun createRandomNumber(): String {
        val rand = Random()
        var randomNum = ""
        repeat(4) {
            val random = rand.nextInt(10).toString()
            randomNum += random
        }
        return randomNum
    }

    private fun makeParams(to: String, randomNum: String): JsonElement {
        val params = "{" +
                "from : ${fromNumber}, " +
                "type : SMS, " to "SMS" +
                "app_version : test app 1.2, " +
                "to : ${to}, " +
                "text : ${randomNum}, " + "}"
        return Json.encodeToJsonElement(params)
    }

    // 인증번호 전송하기
    fun sendSMS(request: SmsRequest): SmsResponse {
        val coolsms = Message(apiKey, apiSecret)
        val map = HashMap<String, JsonElement>()

        // 랜덤한 인증 번호 생성
        val randomNum = createRandomNumber()
        println(randomNum)

        // 발신 정보 설정
        val params = hashMapOf(Pair(request.phoneNumber, makeParams(request.phoneNumber, randomNum)))

        try {
            val obj = coolsms.send(params) as JsonObject
            println(obj.toString())
        } catch (e: CoolSmsException) {
            println("SMS 전송 오류: ${e.message}, 코드: ${e.code}")
        }

        return SmsResponse(randomNum)
    }

    fun verifySms(request: VerifySmsRequest): String {
        // 인증번호를 받지 않았거나 인증번호가 존재하지 않는 경우 예외 처리
        if (!smsCertification.hasKey(request.phoneNumber)) {
            throw IllegalArgumentException("인증번호를 받지 않았거나, 인증번호가 존재하지 않습니다.")
        }
        // 인증번호가 일치하지 않는 경우 예외 처리
        if (!isVerify(request)) {
            throw IllegalArgumentException("인증번호가 일치하지 않습니다.")
        }
        else{
            smsCertification.deleteSmsCertification(request.phoneNumber)
        }
        return "인증 완료되었습니다."
    }

    private fun isVerify(request: VerifySmsRequest): Boolean {
        val certificationNumber = smsCertification.getSmsCertification(request.phoneNumber)
        return certificationNumber != null && certificationNumber == request.randomNumber
    }

    fun createSmsCertification(phoneNumber: String, certificationNumber: String, limitTimeInSeconds: Long = 300) {
        smsCertification.createSmsCertification(phoneNumber, certificationNumber, limitTimeInSeconds)
    }
}
