import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

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