package me.harry.jpa.shop.domain

import javax.persistence.Entity

@Entity
class Book(
        override val name: String,
        override val price: Int,
        override val stockQuantity: Int,
        var author: String,
        val isbn: String,
) : Item(name = name, price = price, stockQuantity = stockQuantity)