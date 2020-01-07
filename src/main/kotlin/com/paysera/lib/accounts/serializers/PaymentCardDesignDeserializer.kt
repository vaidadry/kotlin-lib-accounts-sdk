package com.paysera.lib.accounts.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.cards.PaymentCardDesign
import java.lang.reflect.Type

class PaymentCardDesignDeserializer : JsonDeserializer<PaymentCardDesign> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): PaymentCardDesign {
        val pinCode = json.asJsonObject.get("pin_code").asString
        return PaymentCardDesign(
            json.asJsonObject.get("id").asInt,
            json.asJsonObject.get("name").asString,
            json.asJsonObject.get("visual_type").asString,
            json.asJsonObject.get("selector_color").asString,
            json.asJsonObject.get("priority").asInt,
            json.asJsonObject.get("active").asBoolean,
            json.asJsonObject.get("visual_url").asString
        )
    }
}