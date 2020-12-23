package me.harry.jpa.shop.application

import me.harry.jpa.shop.presentation.exception.NotFoundException
import me.harry.jpa.shop.domain.Order
import me.harry.jpa.shop.infrastructure.OrderRepository
import me.harry.jpa.shop.presentation.exception.ResponseException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.Throws

@Service
class OrderService(
        val orderRepository: OrderRepository
) {

    @Transactional
    fun create(order: Order) {
        orderRepository.save(order)
    }

    @Transactional(readOnly = true)
    // Throws를 붙이고 떼고 차이를 직접 보여드릴 것
    @Throws(ResponseException::class)
    fun read(id: Long): Order {
        return orderRepository.findByIdOrNull(id) ?: throw NotFoundException.ORDER.get()
    }

    fun update() {

    }

    fun delete() {

    }
}