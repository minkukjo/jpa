package me.harry.jpa.shop.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Address(
        @Column(length = 10)
        var city: String,

        @Column(length = 20)
        var street: String,

        @Column(length = 5)
        var zipcode: String,
) {
    fun makeFullAddress(): String = "$city $street $zipcode"
}