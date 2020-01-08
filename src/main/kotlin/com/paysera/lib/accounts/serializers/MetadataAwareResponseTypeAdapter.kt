package com.paysera.lib.accounts.serializers

import com.google.gson.*
import com.paysera.lib.accounts.entities.common.Metadata
import com.paysera.lib.accounts.entities.common.MetadataAwareResponse
import java.lang.reflect.Type

class MetadataAwareResponseTypeAdapter<T>(private val clazz: Class<T>) : JsonDeserializer<MetadataAwareResponse<T>> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext): MetadataAwareResponse<T>? {
        if(json == null) {
            return null
        }
        val items = arrayListOf<T>()
        val keys = json.asJsonObject?.keySet()
        val itemsKey = if(keys?.contains("items")!!) "items" else keys.first { it != "_metadata" }

        json.asJsonObject?.getAsJsonArray(itemsKey)?.forEach {
            items.add(context.deserialize(it, clazz))
        }

        val metadata = context.deserialize<Metadata>(json.asJsonObject?.getAsJsonObject("_metadata"), Metadata::class.java)

        return MetadataAwareResponse(items, metadata)
    }
}