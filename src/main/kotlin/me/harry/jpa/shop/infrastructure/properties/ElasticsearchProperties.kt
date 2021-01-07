package me.harry.jpa.shop.infrastructure.properties

import org.apache.http.HttpHost
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "elasticsearch")
class ElasticsearchProperties {
    lateinit var hosts: List<String>

    fun setHosts(): Array<HttpHost> {
        return hosts.map { HttpHost.create(it) }.toTypedArray()
    }

}