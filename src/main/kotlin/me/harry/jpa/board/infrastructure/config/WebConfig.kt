package me.harry.jpa.board.infrastructure.config

import me.harry.jpa.board.infrastructure.interceptor.PostInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(val postInterceptor: PostInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(postInterceptor)
                .addPathPatterns("/**")
    }
}