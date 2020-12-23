package me.harry.jpa.shop.infrastructure.aop.log

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LogAspect {

    private val log = LoggerFactory.getLogger(javaClass)

    @Before("@annotation(LogExecutionTime)")
    fun before() {
//        println("=====AOP Before!=====")
    }

    @After("@annotation(LogExecutionTime)")
    fun after() {
//        println("=====AOP After!=====")
    }

    @Around("@annotation(LogExecutionTime)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
//        val start = System.currentTimeMillis()
//        println("=====Around Start!=====")
        val proceed = joinPoint.proceed()
//        val executionTime = System.currentTimeMillis() - start
//        println("=====Around Finish! ${executionTime}ms=====")
        return proceed
    }

    @AfterReturning(value = "@annotation(LogExecutionTime)", returning = "resource")
    fun afterReturning(resource: Any?) {
//        println("=====after returning! $resource=====")
    }
}