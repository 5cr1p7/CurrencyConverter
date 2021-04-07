package com.ramilkapev.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.ramilkapev.currencyconverter.ExchangeRates
import com.ramilkapev.currencyconverter.data.ExchangeDao

class ExchangeRepository(private val exchangeDao: ExchangeDao) {
    private val webApi: WebApi = TODO()

//    val allRates: LiveData<ExchangeRates> = exchangeDao.getRates()

//    fun fetchRates() = webApi.getRates()

//    fun insert(exchangeRates: ExchangeRates) {
//        exchangeDao.insert(exchangeRates)
//    }
}