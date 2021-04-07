package com.ramilkapev.currencyconverter.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

object MapStringConverter {
    @TypeConverter
    @JvmStatic
    fun stringToMap(key: JsonElement): Map<String, Double> {
        return Gson().fromJson(key,  object : TypeToken<Map<String, Double>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun mapToString(key: Map<String, Double>?): String {
        return key?.keys?.toString() ?: ""
    }
}