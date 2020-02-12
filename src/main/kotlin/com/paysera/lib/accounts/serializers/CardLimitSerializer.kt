package com.paysera.lib.accounts.serializers

import com.google.gson.*
import com.paysera.lib.accounts.entities.CardLimit
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.lang.reflect.Type
import java.math.RoundingMode

class CardLimitSerializer : JsonDeserializer<CardLimit>, JsonSerializer<CardLimit> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CardLimit {
        val jsonObject = json.asJsonObject.getAsJsonObject("amount")
        var amount = jsonObject.get("amount").asBigDecimal
        amount = amount.setScale(2, RoundingMode.FLOOR)
        val currency = jsonObject.get("currency").asString
        return CardLimit(Money.of(CurrencyUnit.of(currency), amount))
    }

    override fun serialize(
        src: CardLimit,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()

        val amountObject = JsonObject()
        amountObject.addProperty("amount", src.amount.amount.toInt())
        amountObject.addProperty("currency", src.amount.currencyUnit.toString())

        jsonObject.add("amount", amountObject)
        return jsonObject
    }
}