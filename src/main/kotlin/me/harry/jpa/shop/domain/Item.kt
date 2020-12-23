package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "item_id")
        val id: Long? = null,

        @Column
        val name: String,

        @Column
        val price: Int,

        @Column
        val stockQuantity: Int,
)