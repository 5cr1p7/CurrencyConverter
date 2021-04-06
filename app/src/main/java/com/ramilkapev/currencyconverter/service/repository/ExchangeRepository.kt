package com.ramilkapev.currencyconverter.service.repository

import androidx.lifecycle.LiveData
import com.ramilkapev.currencyconverter.ExchangeDao
import com.ramilkapev.currencyconverter.ExchangeRates

class ExchangeRepository(private val exchangeDao: ExchangeDao) {
    private val webApi: WebApi = TODO()

    val allRates: LiveData<ExchangeRates> = exchangeDao.getRates()

//    fun fetchRates() = webApi.getRates()

//    fun insert(exchangeRates: ExchangeRates) {
//        exchangeDao.insert(exchangeRates)
//    }
}