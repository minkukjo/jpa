package me.harry.jpa.shop.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_id")
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "member_id")
        val member: Member,

        @OneToMany(mappedBy = "order")
        val orderItems: MutableList<OrderItem> = mutableListOf(),

        @Column
        val orderDate: LocalDateTime = LocalDateTime.now(),

        @Column
        @Convert(converter = OrderStatusConverter::class)
        val orderStatus: OrderStatus,
) {
    fun addOrderItem(orderItem: OrderItem) {
        this.orderItems.add(orderItem)
        orderItem.order = this
    }
}