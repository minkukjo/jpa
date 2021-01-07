package me.harry.jpa.shop.presentation.exception

import org.springframework.http.HttpStatus
import java.lang.Exception

class ResponseException : Exception {

    private var status: Int = 200
    private var _code: Int = 200
    private var _message: String = ""


    constructor(responseException: ResponseException) {
        this.status = responseException.status
        this._code = responseException._code
        this._message = responseException._message
    }

    constructor(httpStatus: HttpStatus, code: Int, message: String) {
        this.status = httpStatus.value()
        this._code = code
        this._message = message
    }

    constructor(throwable: Throwable, httpStatus: HttpStatus, message: String) : super(throwable) {
        this.status = httpStatus.value()
        this._code = httpStatus.value()
        this._message = message
    }
}