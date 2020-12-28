package me.harry.jpa.shop.domain

import javax.persistence.Entity

@Entity
class Movie(
        override val name: String,
        override val price: Int,
        override val stockQuantity: Int,
        val director: String,
        val actor: String,
) : Item(name = name, price = price, stockQuantity = stockQuantity)