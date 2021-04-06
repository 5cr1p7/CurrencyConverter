package com.ramilkapev.currencyconverter

import android.app.Application
import com.ramilkapev.currencyconverter.service.repository.WebApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConverterApp: Application() {

    companion object {
        private const val BASE_URL = "https://open.exchangerate-api.com/v6/"
    }

    lateinit var webApi: WebApi

    override fun onCreate() {
        super.onCreate()

        networkService()
    }

    private fun networkService() {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        webApi = retrofit.create(WebApi::class.java)
    }




}