package me.harry.jpa.shop.infrastructure.filter

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val wrappingRequest = ContentCachingRequestWrapper(request)
        val wrappingResponse = ContentCachingResponseWrapper(response)
//        println("=====Filter Request : $wrappingRequest=====")
        filterChain.doFilter(wrappingRequest, wrappingResponse)
        // ES로 로깅 발싸
//        val readValue = String(wrappingResponse.contentAsByteArray)
//        println("=====Filter Response : $readValue=====")

        wrappingResponse.copyBodyToResponse()
    }
}