package me.harry.jpa.shop.domain

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import javax.persistence.AttributeConverter
import javax.persistence.Convert
import javax.persistence.Converter

@Serializable
enum class OrderStatus(val status: String) {
    @SerialName("ORDER")
    ORDER("order"),

    @SerialName("CANCEL")
    CANCEL("cancel");
}

@Converter
class OrderStatusConverter : AttributeConverter<OrderStatus, String> {
    override fun convertToDatabaseColumn(attribute: OrderStatus?): String? {
        return attribute?.status?.toUpperCase()
    }

    override fun convertToEntityAttribute(dbData: String?): OrderStatus? {
        return dbData?.let { OrderStatus.valueOf(it.toUpperCase()) }
    }
}