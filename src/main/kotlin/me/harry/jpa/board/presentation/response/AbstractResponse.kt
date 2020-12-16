package me.harry.jpa.board.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

abstract class AbstractResponse {
    @JsonProperty("_code")
    private var _code: Int? = null

    @JsonProperty("_message")
    private var _message: String? = null

    fun set(httpStatus: HttpStatus) {
        this._code = httpStatus.value()
        this._message = httpStatus.reasonPhrase
    }

    fun set(_code: Int, _message: String) {
        this._code = _code
        this._message = _message
    }
}