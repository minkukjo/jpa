package me.harry.jpa.shop.infrastructure.config

import me.harry.jpa.shop.infrastructure.properties.ElasticsearchProperties
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticsearchConfig {

    @Bean
    fun client(properties: ElasticsearchProperties): RestHighLevelClient {
        return RestHighLevelClient(RestClient.builder(*properties.setHosts()))
    }
}