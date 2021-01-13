package me.harry.jpa.shop.infrastructure.config

import me.harry.jpa.shop.infrastructure.cache.Caches
import me.harry.jpa.shop.infrastructure.cache.SimpleRedisCacheWriter
import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.CacheKeyPrefix
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import java.time.Duration

@Configuration
class RedisConfig(
        @Value("\${spring.redis.host}")
        val host: String,
        @Value("\${spring.redis.port}")
        val port: Int,
        @Value("\${spring.redis.password}")
        val password: String,
        @Value("\${spring.redis.connect-timeout}")
        val timeout: Long,
        @Value("\${spring.redis.lettuce.pool.max-idle}")
        val maxIdle: Int,
        @Value("\${spring.redis.lettuce.pool.min-idle}")
        val minIdle: Int,
        @Value("\${spring.redis.lettuce.pool.max-active}")
        val maxActive: Int,
        @Value("\${spring.redis.lettuce.pool.max-wait}")
        val maxWait: Long,
) {

    companion object {
        const val REDIS_MANAGER = "redis"
    }

    @Bean
    fun genericObjectPoolConfig(): GenericObjectPoolConfig<Any> {
        val poolConfig: GenericObjectPoolConfig<Any> = GenericObjectPoolConfig()
        // 리소스 풀이 전부 소진되었을 때 호출자가 대기해야하는지의 여부 기본 값은 true
        // false로 설정하면 maxWaitMills로 지정한 시간이 사용되지 않으며 idle connection이 사용가능할 때 까지 호출이 block 된다.
        poolConfig.blockWhenExhausted = false
        poolConfig.maxIdle = maxIdle
        poolConfig.minIdle = minIdle
        poolConfig.maxTotal = maxActive
        poolConfig.maxWaitMillis = maxWait
        return poolConfig

    }

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory {
        val redisConfig = RedisStandaloneConfiguration()
        redisConfig.database = 0
        redisConfig.hostName = host
        redisConfig.port = port
        if (redisConfig.password.isPresent) {
            redisConfig.password = RedisPassword.of(password)
        }

        val clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeout))
                .poolConfig(genericObjectPoolConfig())
                .build()

        return LettuceConnectionFactory(redisConfig, clientConfig)
    }

    @Primary
    @Bean(name = [REDIS_MANAGER])
    fun redisCacheManager(): CacheManager {

        val redisCacheWriter = SimpleRedisCacheWriter(lettuceConnectionFactory())

        val defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(Caches.DEFAULT_EXPIRE_TIME))
                .disableCachingNullValues()
                // CacheKeyPrefix는 재정의가 가능하다
                .computePrefixWith(CacheKeyPrefix.simple())

        val cacheConfigMap = mutableMapOf<String, RedisCacheConfiguration>()
        cacheConfigMap[Caches.ALL.Order.NAME] = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(Caches.ALL.Order.TTL))
        return RedisCacheManager.builder(redisCacheWriter)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(cacheConfigMap)
                .disableCreateOnMissingCache()
                .build()

    }

}