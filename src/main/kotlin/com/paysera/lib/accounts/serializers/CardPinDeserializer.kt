package com.paysera.lib.accounts.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.cards.CardPin
import java.lang.reflect.Type

class CardPinDeserializer : JsonDeserializer<CardPin> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CardPin {
        val pinCode = json.asJsonObject.get("pin_code").asString
        return CardPin(pinCode)
    }
}