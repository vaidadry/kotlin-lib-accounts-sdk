package com.paysera.lib.accounts.serializers

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.paysera.lib.accounts.entities.transfers.TransferNotification
import java.io.IOException
import java.util.ArrayList

class TransferNotificationDeserializer: TypeAdapter<List<TransferNotification>>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: List<TransferNotification>) {
        out.beginObject()
        for (item in value) {
            out.name(item.type)
            out.beginObject()
            out.name("email").value(item.email)
            out.name("locale").value(item.locale)
            out.endObject()
        }
        out.endObject()
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): List<TransferNotification> {
        val transferNotifications = ArrayList<TransferNotification>()
        `in`.beginObject()
        while (`in`.hasNext()) {
            val transferNotification = TransferNotification()
            transferNotification.type = `in`.nextName()
            `in`.beginObject()
            if (`in`.nextName() == "email") {
                transferNotification.email = `in`.nextString()
            }
            if (`in`.nextName() == "locale") {
                transferNotification.locale = `in`.nextString()
            }
            `in`.endObject()
            transferNotifications.add(transferNotification)
        }
        `in`.endObject()
        return transferNotifications
    }
}
