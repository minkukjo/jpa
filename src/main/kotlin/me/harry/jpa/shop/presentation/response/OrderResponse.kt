package me.harry.jpa.shop.presentation.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import me.harry.jpa.shop.domain.Member
import me.harry.jpa.shop.domain.Order


class OrderResponse(order: Order) : OkResponse() {
    val id: Long? = order.id

    val orderStatus = order.orderStatus
}