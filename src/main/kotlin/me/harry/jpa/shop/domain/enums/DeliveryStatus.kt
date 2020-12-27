package me.harry.jpa.shop.domain.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Serializable
enum class DeliveryStatus(val status: String) {
    @SerialName("order")
    ORDER("order"),

    @SerialName("cancel")
    CANCEL("cancel");
}

@Converter
class DeliveryStatusConverter : AttributeConverter<DeliveryStatus, String> {
    override fun convertToDatabaseColumn(attribute: DeliveryStatus?): String {
        return attribute?.status ?: ""
    }

    override fun convertToEntityAttribute(dbData: String?): DeliveryStatus {
        return DeliveryStatus.valueOf(dbData ?: "");
    }
}