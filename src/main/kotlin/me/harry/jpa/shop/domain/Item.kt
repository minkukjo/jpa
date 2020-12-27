package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "item_id")
        val id: Long? = null,

        val name: String,

        val price: Int,

        val stockQuantity: Int,

        @ManyToMany(mappedBy = "items")
        val item: MutableList<Category> = mutableListOf()
)