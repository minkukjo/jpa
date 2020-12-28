package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.ManyToMany

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
abstract class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "item_id")
        val id: Long? = null,

        open val name: String,

        open val price: Int,

        open val stockQuantity: Int,

        @ManyToMany(mappedBy = "items")
        val item: MutableList<Category> = mutableListOf()
) : BaseEntity()