package com.example.removalbackend.domain.sms.repository

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class SmsCertification(private val stringRedisTemplate: StringRedisTemplate) {
    private val PREFIX = "sms:" // key값이 중복되지 않도록 상수 선언
    private val LIMIT_TIME = 3 * 60 // 인증번호 유효 시간

    // Redis에 저장
    fun createSmsCertification(phone: String, certificationNumber: String) {
        stringRedisTemplate.opsForValue()
            .set("$PREFIX$phone", certificationNumber, Duration.ofSeconds(LIMIT_TIME.toLong()))
    }

    // 휴대전화 번호에 해당하는 인증번호 불러오기
    fun getSmsCertification(phone: String): String? {
        return stringRedisTemplate.opsForValue().get("$PREFIX$phone")
    }

    // 인증 완료 시, 인증번호 Redis에서 삭제
    fun deleteSmsCertification(phone: String) {
        stringRedisTemplate.delete("$PREFIX$phone")
    }

    // Redis에 해당 휴대번호로 저장된 인증번호가 존재하는지 확인
    fun hasKey(phone: String): Boolean {
        return stringRedisTemplate.hasKey("$PREFIX$phone")
    }
}
