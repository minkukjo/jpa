package me.harry.jpa.shop.presentation.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(Throwable::class)
    fun onResponseException(ex: Throwable) {
        val toResponseException = toResponseException(ex)
        // TODO : 에러 발생했으니 알람 발생
        // TODO : 에러 페이지로 리다이렉션
        println("에러 발생")
        println(toResponseException.message)
        println("에러 발생")
    }

    private fun toResponseException(ex: Throwable): ResponseException {
        return when (ex) {
            is ResponseException -> {
                ex
            }
            else -> {
                ResponseException(ex, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
            }
        }
    }
}