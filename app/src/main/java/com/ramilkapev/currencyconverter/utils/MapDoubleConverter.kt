package com.ramilkapev.currencyconverter.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

object MapDoubleConverter {
    @TypeConverter
    @JvmStatic
    fun doubleToMap(value: JsonElement): Map<String, Double> {
        return Gson().fromJson(value,  object : TypeToken<Map<String, Double>>() {}.type)
    }
    @TypeConverter
    @JvmStatic
    fun mapToDouble(value: Map<String, Double>): Array<Double> {
        return value.values.toTypedArray()
    }
}