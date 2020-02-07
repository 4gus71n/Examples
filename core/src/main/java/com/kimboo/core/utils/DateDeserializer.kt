package com.kimboo.core.utils

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {
    override fun deserialize(
        element: JsonElement,
        arg1: Type,
        arg2: JsonDeserializationContext
    ): Date? {
        val dateLong = element.asLong
        try {
            return Date(dateLong)
        } catch (e: ParseException) {
            Log.w("DateDeserializer", "Failed to parse Date due to: $e")
            return null
        }
    }
}