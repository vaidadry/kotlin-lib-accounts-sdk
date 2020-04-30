package com.paysera.lib.accounts.serializers

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.paysera.lib.accounts.entities.transfers.TransferNotification
import java.io.IOException
import java.util.ArrayList

class TransferNotificationDeserializer : TypeAdapter<List<TransferNotification>>() {

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
    override fun read(reader: JsonReader): List<TransferNotification> {
        val transferNotifications = ArrayList<TransferNotification>()
        reader.beginObject()
        while (reader.hasNext()) {
            val transferNotification = TransferNotification()
            transferNotification.type = reader.nextName()
            reader.beginObject()
            if (reader.nextName() == "locale") {
                transferNotification.locale = reader.nextString()
            }
            if (reader.nextName() == "email") {
                transferNotification.email = reader.nextString()
            }
            reader.endObject()
            transferNotifications.add(transferNotification)
        }
        reader.endObject()
        return transferNotifications
    }
}
