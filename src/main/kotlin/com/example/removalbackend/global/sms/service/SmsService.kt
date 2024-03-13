import net.nurigo.sdk.Message
import net.nurigo.sdk.exceptions.CoolsmsException
import org.aspectj.bridge.Message
import java.util.Random

@Throws(CoolsmsException::class)
fun phoneNumberCheck(to: String): String {

    val rand = Random()
    var numStr = ""
    repeat(4) {
        val ran = rand.nextInt(10).toString()
        numStr += ran
    }

    val params = hashMapOf(
        "to" to to,    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
        "from" to "01072791324",    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        "type" to "sms",
        "text" to "인증번호는 [$numStr] 입니다."
    )

    coolsms.send(params) // 메시지 전송

    return numStr
}
