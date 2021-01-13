package me.harry.jpa.shop.infrastructure.cache

import org.springframework.data.redis.cache.CacheStatistics
import org.springframework.data.redis.cache.CacheStatisticsCollector
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnection
import org.springframework.data.redis.connection.RedisStringCommands
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ScanOptions
import org.springframework.data.redis.core.types.Expiration
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.concurrent.TimeUnit

class SimpleRedisCacheWriter(
        private val connectionFactory: LettuceConnectionFactory
) : RedisCacheWriter {

    companion object {
        const val SCAN_COUNT = 1024L
    }

    override fun getCacheStatistics(cacheName: String): CacheStatistics {
        TODO("Not yet implemented")
    }

    override fun put(name: String, key: ByteArray, value: ByteArray, ttl: Duration?) {
        execute { connection ->
            run {
                if (shouldExpireWithin(ttl)) {
                    connection.set(key, value, Expiration.from(ttl!!.toMillis(), TimeUnit.MILLISECONDS), RedisStringCommands.SetOption.upsert())
                } else {
                    connection.set(key, value)
                }
            }
            return@execute "OK"
        }
    }

    override fun get(name: String, key: ByteArray): ByteArray? {
        return execute { connection -> connection.get(key) }
    }

    override fun putIfAbsent(name: String, key: ByteArray, value: ByteArray, ttl: Duration?): ByteArray? {
        return execute { connection ->
            run {
                connection.setNX(key, value)
                if (shouldExpireWithin(ttl)) {
                    connection.pExpire(key, ttl!!.toMillis())
                }

                return@execute connection.get(key)
            }
        }
    }

    override fun remove(name: String, key: ByteArray) {
        execute { connection -> connection.del(key) }
    }

    override fun clean(name: String, pattern: ByteArray) {
        val keys = mutableListOf<ByteArray>()

        val scanOptions = ScanOptions.scanOptions()
                .count(SCAN_COUNT)
                .match(String(pattern, StandardCharsets.UTF_8))
                .build()

        execute { connection ->
            run {
                val cursor = connection.scan(scanOptions)
                if (cursor.hasNext()) {
                    do {
                        keys.add(cursor.next())
                    } while (cursor.hasNext())
                    connection.del(*keys.toTypedArray())
                    keys.clear()
                }
            }
            return@execute "OK"
        }
    }

    override fun clearStatistics(name: String) {
        TODO("Not yet implemented")
    }

    override fun withStatisticsCollector(cacheStatisticsCollector: CacheStatisticsCollector): RedisCacheWriter {
        TODO("Not yet implemented")
    }

    private fun <T> execute(callback: (connection: RedisConnection) -> T): T {
        val connection = connectionFactory.connection
        // 코틀린 use는 자바의 try-with-resource같은 구문
        connection.use {
            return callback(it)
        }
    }
}

private fun shouldExpireWithin(ttl: Duration?): Boolean {
    return ttl?.run {
        return !this.isZero && !this.isNegative
    } ?: false
}