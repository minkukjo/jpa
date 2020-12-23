package me.harry.jpa.shop.presentation.exception

import java.util.function.Supplier

interface ResponseDefinition : Supplier<ResponseException> {

    fun getResponseException(): ResponseException

    override fun get(): ResponseException {
        return getResponseException().clone()
    }
}