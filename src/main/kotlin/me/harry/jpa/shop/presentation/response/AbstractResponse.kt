package me.harry.jpa.shop.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import org.springframework.http.HttpStatus

@Serializable
abstract class AbstractResponse {
    private var _code: Int? = null
    
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