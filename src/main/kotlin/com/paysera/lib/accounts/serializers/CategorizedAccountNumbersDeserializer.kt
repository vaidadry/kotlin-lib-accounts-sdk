package com.paysera.lib.accounts.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.cards.CategorizedAccountNumbers
import java.lang.reflect.Type

class CategorizedAccountNumbersDeserializer : JsonDeserializer<List<CategorizedAccountNumbers>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): List<CategorizedAccountNumbers> {
        val items = arrayListOf<CategorizedAccountNumbers>()
        json?.asJsonObject?.getAsJsonArray("items")?.forEach {
            items.add(context.deserialize(it, CategorizedAccountNumbers::class.java))
        }
        return items
    }
}