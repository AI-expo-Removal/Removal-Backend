import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sms")
class SmsController(
    private val messageService: MessageService
) {

    @PostMapping("/send")
    fun sendSms(@RequestParam to: String, @RequestParam message: String): String {
        messageService.sendSMS(message)
        return "SMS 발송 완료"
    }
}