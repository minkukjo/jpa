package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val name: String,

        @ManyToOne
        @JoinColumn(name = "parent_id")
        val parent: Category? = null,

        @OneToMany(mappedBy = "parent")
        val child: MutableList<Category> = mutableListOf(),

        @ManyToMany
        @JoinTable(
                name = "category_item",
                joinColumns = [JoinColumn(name = "category_id")],
                inverseJoinColumns = [JoinColumn(name = "item_id")]
        )
        val items: MutableList<Item> = mutableListOf(),
)