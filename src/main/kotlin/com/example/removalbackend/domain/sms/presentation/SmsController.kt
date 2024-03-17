import com.example.removalbackend.domain.sms.presentation.dto.request.SmsRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val messageService: MessageService
) {

    @PostMapping("/send")
    fun sendSms(@RequestParam smsRequest: SmsRequest): String {
        messageService.sendSMS(smsRequest)
        return "SMS 발송 완료"
    }
}