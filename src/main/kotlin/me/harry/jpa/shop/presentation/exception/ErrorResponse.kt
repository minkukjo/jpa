package me.harry.jpa.shop.presentation.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import java.time.LocalDate
import java.time.LocalDateTime

data class ErrorResponse(val status: HttpStatus, val error: String, val message: String, val timestamp: LocalDateTime, val bindingErrors: List<String>) {

    constructor(status: HttpStatus, message: String, bindingErrors: List<String>) : this(status, status.reasonPhrase, message, LocalDateTime.now(), bindingErrors)
    constructor(status: HttpStatus, error: String, message: String) : this(status, error, message, LocalDateTime.now(), ArrayList<String>())
    constructor(status: HttpStatus, message: String) : this(status, status.reasonPhrase, message, LocalDateTime.now(), ArrayList<String>())

}

class ErrorResponseEntity : ResponseEntity<ErrorResponse> {

    constructor(body: ErrorResponse) : super(body, body.status)
    constructor(body: ErrorResponse, headers: MultiValueMap<String, String>) : super(body, headers, body.status)


    companion object {

        fun badRequest(message: String) = ErrorResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST, message))
        fun badRequest(message: String, bindingErrors: List<String>) = ErrorResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST, message, bindingErrors))
        fun notFound(message: String) = ErrorResponseEntity(ErrorResponse(HttpStatus.NOT_FOUND, message))
        fun serverError(message: String) = ErrorResponseEntity(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message))

    }

}