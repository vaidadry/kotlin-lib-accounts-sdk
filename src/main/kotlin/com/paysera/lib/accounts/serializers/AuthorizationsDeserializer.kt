package com.paysera.lib.accounts.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.paysera.lib.accounts.entities.authorizations.Authorization
import java.lang.reflect.Type

class AuthorizationsDeserializer : JsonDeserializer<List<Authorization>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): List<Authorization> {
        val authorizations = arrayListOf<Authorization>()
        json?.asJsonObject?.getAsJsonArray("items")?.forEach {
            authorizations.add(context.deserialize(it, Authorization::class.java))
        }
        return authorizations
    }
}