package me.harry.jpa.board.infrastructure.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import me.harry.jpa.board.presentation.response.PostResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingFilter : OncePerRequestFilter() {
    companion object {
        private val mapper = jacksonObjectMapper()
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val wrappingRequest = ContentCachingRequestWrapper(request)
        val wrappingResponse = ContentCachingResponseWrapper(response)
        println("=====Filter Request : $wrappingRequest=====")
        filterChain.doFilter(wrappingRequest, wrappingResponse)
        // ES로 로깅 발싸
        val readValue = String(wrappingResponse.contentAsByteArray)
        println("=====Filter Response : $readValue=====")

        wrappingResponse.copyBodyToResponse()
    }
}