package me.harry.jpa.shop.infrastructure.aop.log

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime