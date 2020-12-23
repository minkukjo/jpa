package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_item_id")
        val id: Long? = null,

        @Column(name = "order_id")
        val orderId: Long,

        @Column(name = "item_id")
        val itemId: Long,

        @Column
        val orderPrice: Int,

        @Column
        val count: Int,
)