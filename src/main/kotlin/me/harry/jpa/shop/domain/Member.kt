package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        val id: Long? = null,

        @Column
        val name: String,

        @Column
        val city: String,

        @Column
        val street: String,
        
        @Column
        val zipcode: String,
)