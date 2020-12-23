package me.harry.jpa.shop.infrastructure.config

import me.harry.jpa.shop.infrastructure.interceptor.OrderInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(val orderInterceptor: OrderInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(orderInterceptor)
                .addPathPatterns("/**")
    }
}