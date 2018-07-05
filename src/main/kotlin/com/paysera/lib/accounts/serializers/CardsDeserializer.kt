package com.paysera.lib.accounts.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.cards.Card
import java.lang.reflect.Type

class CardsDeserializer : JsonDeserializer<List<Card>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext): List<Card> {
        val cards = arrayListOf<Card>()
        json?.asJsonObject?.getAsJsonArray("cards")?.forEach { cards.add(context.deserialize(it, Card::class.java)) }
        return cards
    }
}