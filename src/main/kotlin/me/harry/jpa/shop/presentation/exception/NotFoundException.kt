package me.harry.jpa.shop.presentation.exception

import org.springframework.http.HttpStatus

enum class NotFoundException(
        private val status: HttpStatus,
        private val code: Int,
        private val message: String,
) {
    ORDER(HttpStatus.BAD_REQUEST, NotFoundException.NOT_FOUND_CODE, "Not Found Order");

    companion object {
        const val NOT_FOUND_CODE = 404;
    }

    fun get(): ResponseException {
        return ResponseException(this.status, this.code, this.message)
    }
}