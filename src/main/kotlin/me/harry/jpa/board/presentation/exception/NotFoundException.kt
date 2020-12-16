package me.harry.jpa.board.presentation.exception

import org.springframework.http.HttpStatus

enum class NotFoundException(
        private val status: HttpStatus,
        private val code: Int,
        private val message: String,
) : ResponseDefinition {
    POST(HttpStatus.BAD_REQUEST, NotFoundException.NOT_FOUND_CODE, "Not Found Post");

    companion object {
        const val NOT_FOUND_CODE = 404;
    }

    override fun getResponseException(): ResponseException {
        return ResponseException(this.status, this.code, this.message)
    }
}