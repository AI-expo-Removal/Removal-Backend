import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Value("\${REDIS_HOST}")
    private lateinit var redisHost: String

    @Value("\${REDIS_PORT}")
    private var redisPort: Int = 0

//    @Value("\${REDIS_PASSWORD}")
//    private lateinit var redisPwd: RedisPassword

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration().apply {
            hostName = redisHost
            port = redisPort
//            password = redisPwd
        }
        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> = RedisTemplate<ByteArray, ByteArray>().apply {
        setConnectionFactory(redisConnectionFactory())
    }
}

class RedisTemplateFactory(
    private val redisConnectionFactory: RedisConnectionFactory,
    private val objectMapper: ObjectMapper
) {
    private val stringRedisSerializer = StringRedisSerializer()

    fun getRedisTemplate(): RedisTemplate<String, String> {
        return getRedisTemplate(stringRedisSerializer, stringRedisSerializer)
    }

    private fun <K, V> getRedisTemplate(
        keySerializer: RedisSerializer<K>,
        valueSerializer: RedisSerializer<V>
    ): RedisTemplate<K, V> {
        return RedisTemplate<K, V>().apply {
            this.keySerializer = keySerializer
            this.hashKeySerializer = keySerializer
            this.valueSerializer = valueSerializer
            this.hashValueSerializer = valueSerializer
            setConnectionFactory(redisConnectionFactory)
            this.afterPropertiesSet()
        }
    }
}