package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_item_id")
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "order_id")
        var order: Order,

        @ManyToOne
        @JoinColumn(name = "item_id")
        val item: Item,

        @Column
        val orderPrice: Int,

        @Column
        val count: Int,
)