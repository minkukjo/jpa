package me.harry.jpa.shop.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_id")
        val id: Long? = null,

        @Column(name = "member_id")
        val memberId: Long,

        @Column
        val orderDate: LocalDateTime,

        @Column
        @Convert(converter = OrderStatusConverter::class)
        val orderStatus: OrderStatus,
)