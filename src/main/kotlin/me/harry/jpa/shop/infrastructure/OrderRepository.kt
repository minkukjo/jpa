package me.harry.jpa.shop.infrastructure

import me.harry.jpa.shop.domain.Member
import me.harry.jpa.shop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}