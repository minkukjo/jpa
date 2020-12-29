package me.harry.jpa.shop.domain

import me.harry.jpa.shop.domain.enums.DeliveryStatus
import me.harry.jpa.shop.domain.enums.DeliveryStatusConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne


@Entity
class Delivery(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
        val order: Order,

        @Embedded
        val address: Address?,

        @Convert(converter = DeliveryStatusConverter::class)
        val status: DeliveryStatus
)