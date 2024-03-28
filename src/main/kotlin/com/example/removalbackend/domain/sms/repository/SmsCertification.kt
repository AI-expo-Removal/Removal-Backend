//package com.example.removalbackend.domain.sms.repository
//
//import org.springframework.data.redis.core.StringRedisTemplate
//import org.springframework.stereotype.Repository
//import java.time.Duration
//
//
//@Repository
//class SmsCertification(
//    private val stringRedisTemplate: StringRedisTemplate
//) {
//    //    private val PREFIX = "sms:" // key값이 중복되지 않도록 상수 선언
//    private val LIMIT_TIME = 5 * 60 // 인증번호 유효 시간
//
//    // Redis에 저장
//    fun createSmsCertification(
//        phoneNumber: String,
//        certificationNumber: String,
//        limitTimeInSeconds: Long = LIMIT_TIME.toLong()
//    ) {
//        try {
//            stringRedisTemplate.opsForValue()
//                .set(phoneNumber, certificationNumber, Duration.ofSeconds(limitTimeInSeconds))
//        } catch (e: Exception) {
//            // 로깅 라이브러리를 사용하여 에러 로깅을 할 수 있다. 예: Log.error("Redis operation failed", e)
//            throw IllegalStateException("인증번호 저장에 실패하였습니다.", e)
//        }
//    }
//
//    // 휴대전화 번호에 해당하는 인증번호 불러오기
//    fun getSmsCertification(phoneNumber: String): String? {
//        return stringRedisTemplate.opsForValue().get(phoneNumber)
//    }
//
//    // 인증 완료 시, 인증번호 Redis에서 삭제
//    fun deleteSmsCertification(phoneNumber: String) {
//        stringRedisTemplate.delete(phoneNumber)
//    }
//
//    // Redis에 해당 휴대번호로 저장된 인증번호가 존재하는지 확인
//    fun hasKey(phoneNumber: String): Boolean {
//        return stringRedisTemplate.hasKey(phoneNumber)
//    }
//}
