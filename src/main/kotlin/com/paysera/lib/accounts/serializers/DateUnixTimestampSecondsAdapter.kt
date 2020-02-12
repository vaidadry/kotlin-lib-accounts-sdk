package com.paysera.lib.accounts.serializers

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.util.*

class DateUnixTimestampSecondsAdapter : TypeAdapter<Date>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, date: Date) {
        out.value(date.time / 1000L)
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Date {
        return Date(reader.nextLong() * 1000)
    }
}
