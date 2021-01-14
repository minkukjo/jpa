package me.harry.jpa.shop.application

import me.harry.jpa.shop.domain.Order
import me.harry.jpa.shop.infrastructure.OrderRepository
import me.harry.jpa.shop.infrastructure.cache.Caches
import me.harry.jpa.shop.presentation.exception.DataNotFoundException
import me.harry.jpa.shop.presentation.exception.ErrorResponse
import me.harry.jpa.shop.presentation.exception.TopicNotFoundException
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.Throws

@Service
class OrderService(
        val orderRepository: OrderRepository
) {

    @Transactional
    fun create(order: Order): Order {
        return orderRepository.save(order)
    }

    @Transactional(readOnly = true)
    @Cacheable(value = [Caches.ALL.Order.NAME], key = Caches.ALL.Order.KEY_ID, unless = Caches.UNLESS_NULL)
    @Throws(DataNotFoundException::class)
    fun read(id: Long): Order {
        return orderRepository.findByIdOrNull(id)
                ?: throw TopicNotFoundException(id)
    }

    fun update() {

    }

    fun delete() {

    }
}