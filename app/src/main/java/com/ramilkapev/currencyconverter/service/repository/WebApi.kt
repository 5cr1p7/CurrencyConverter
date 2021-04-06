package com.ramilkapev.currencyconverter.service.repository

import com.ramilkapev.currencyconverter.ExchangeRates
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebApi {
    @GET ("latest/{base_rate}")
    fun getRates(@Path("base_rate") base_rate: String): Call<ExchangeRates>
}