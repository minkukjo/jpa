package me.harry.jpa.shop.presentation.exception

import me.harry.jpa.shop.presentation.exception.ErrorResponseEntity.Companion.notFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.Locale

@ControllerAdvice
class ExceptionHandlers @Autowired constructor(var messageSource: MessageSource) {
    @ExceptionHandler(DataNotFoundException::class)
    fun resourceNotFoundException(exception: DataNotFoundException, locale: Locale) =
            notFound(messageSource.getMessage(exception, locale))
}