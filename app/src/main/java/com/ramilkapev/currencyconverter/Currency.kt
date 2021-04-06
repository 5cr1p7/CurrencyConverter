package com.ramilkapev.currencyconverter

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "rates_table")
data class Currency(
    @ColumnInfo(name = "column_name")
    val name: String,
    @ColumnInfo(name = "column_price")
    val price: Double)

