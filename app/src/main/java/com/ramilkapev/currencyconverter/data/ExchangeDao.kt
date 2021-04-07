package com.ramilkapev.currencyconverter.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExchangeDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(currency: Currency)
//
//    @Transaction
//    suspend fun upsertCurrencies(currencies: List<Currency>) {
//        currencies.forEach { insert(it) }
//    }
//
//    @Transaction
//    suspend fun updateExchangeRates(currencies: List<Currency>) {
//        currencies.forEach { updateExchangeRate(it.name, it.price) }
//    }
//
//    @Query("UPDATE rates_table SET column_price = :column_price WHERE column_name = :column_name")
//    suspend fun updateExchangeRate(column_name: String, column_price: Double)
//
//    @Query("SELECT * from rates_table")
//    fun getRates(): LiveData<ExchangeRates>
}
