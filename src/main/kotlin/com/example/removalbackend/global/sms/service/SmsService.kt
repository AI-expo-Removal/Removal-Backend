import com.example.removalbackend.global.sms.repository.SmsCertification
import kotlinx.serialization.json.JsonObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(
    private val smsCertification: SmsCertification,
) {
    @Value("\${coolsms.apikey}")
    private lateinit var apiKey: String

    @Value("\${coolsms.apisecret}")
    private lateinit var apiSecret: String

    @Value("\${coolsms.fromnumber}")
    private lateinit var fromNumber: String

    private class Message(
        private val apiKey: String,
        private val apiSecret: String
    ) {
        fun send(params: HashMap<String, String>): JsonObject{
            // 실제 전송 로직 작성
            return JsonObject() // 임시로 빈 JSON 객체 반환
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

    private fun makeParams(to: String, randomNum: String): HashMap<String, String> {
        val params = hashMapOf(
            "from" to fromNumber,
            "type" to "SMS",
            "app_version" to "test app 1.2",
            "to" to to,
            "text" to randomNum
        )
        return params
    }

    // 인증번호 전송하기
    fun sendSMS(phoneNumber: String): String {
        val coolsms = Message(apiKey, apiSecret)

        // 랜덤한 인증 번호 생성
        val randomNum = createRandomNumber()
        println(randomNum)

        // 발신 정보 설정
        val params = makeParams(phoneNumber, randomNum)

        try {
            val obj = coolsms.send(params) as JsonObject
            println(obj.toString())
        } catch (e: CoolsmsException) {
            println(e.message)
            println(e.code)
        }

        return "문자 전송이 완료되었습니다."
    }
    fun verifySms(requestDto: UserDto.SmsCertificationDto): String {
        if (isVerify(requestDto)) {
            throw IllegalArgumentException("인증번호가 일치하지 않습니다.")
        }
        smsCertification.deleteSmsCertification(requestDto.phoneNumber)

        return "인증 완료되었습니다."
    }
    private fun isVerify(requestDto: UserDto.SmsCertificationDto): Boolean {
        return !(smsCertification.hasKey(requestDto.phoneNumber) &&
                smsCertification.getSmsCertification(requestDto.phoneNumber)
                    .equals(requestDto.randomNumber))
    }
}

class CoolsmsException(message: String) : Exception(message) {
    var code: Int = 0
}
