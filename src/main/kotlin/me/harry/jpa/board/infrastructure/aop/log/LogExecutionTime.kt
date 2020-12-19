package me.harry.jpa.board.infrastructure.aop.log

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime