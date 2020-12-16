package me.harry.jpa.board.presentation.response

import org.springframework.http.HttpStatus

open class OkResponse : AbstractResponse() {

    init {
        this.set(HttpStatus.OK)
    }
}