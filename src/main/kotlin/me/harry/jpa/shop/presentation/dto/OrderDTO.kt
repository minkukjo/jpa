package me.harry.jpa.shop.presentation.dto

import kotlinx.serialization.Serializable
import me.harry.jpa.shop.domain.Delivery
import me.harry.jpa.shop.domain.Member
import me.harry.jpa.shop.domain.Order
import me.harry.jpa.shop.domain.OrderStatus
import me.harry.jpa.shop.domain.enums.DeliveryStatus
import java.time.LocalDateTime

@Serializable
class OrderDTO(
        private val memberId: Long,
        private val orderStatus: OrderStatus
) {
    fun toOrder(): Order {
        return Order(orderDate = LocalDateTime.now(), orderStatus = orderStatus)
    }
}