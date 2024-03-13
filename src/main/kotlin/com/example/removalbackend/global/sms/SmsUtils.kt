import net.nurigo.sdk.NurigoApp
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.service.DefaultMessageService
import net.nurigo.sdk.message.model.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class SmsUtil {

    @Value("\${coolsms.api.key}")
    private lateinit var apiKey: String

    @Value("\${coolsms.api.secret}")
    private lateinit var apiSecretKey: String

    private lateinit var messageService: DefaultMessageService

    @PostConstruct
    private fun init() {
        messageService = NurigoApp.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr")
    }

    fun sendOne(to: String, verificationCode: String): SingleMessageSentResponse? {
        val message = Message()
        message.from = ("발신번호 입력")
        message.to = to
        message.text = "[Moyiza] 아래의 인증번호를 입력해주세요\n$verificationCode"

        val response = messageService.sendOne(SingleMessageSendingRequest(message))
        return response
    }
}
