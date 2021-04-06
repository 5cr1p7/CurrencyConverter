package com.ramilkapev.currencyconverter.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramilkapev.currencyconverter.ExchangeRates
import com.ramilkapev.currencyconverter.service.repository.WebApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(): ViewModel() {
    private val _youSend = MutableLiveData<Double>()
    val youSend: LiveData<Double> = _youSend

//    private val _keysList = MutableLiveData<String>()
//    val keysList: LiveData<String> = _keysList

    val result: Double = 5.0

    var keysList = arrayOf<String>()
    var valuesList = arrayOf<Double>()

//    private val repo: Repo

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchRates(webApi: WebApi, baseRate: String) {
        webApi.getRates(baseRate).enqueue(object: Callback<ExchangeRates> {
            override fun onResponse(call: Call<ExchangeRates>, response: Response<ExchangeRates>) {
//                keysList = response.body().rates.keys.toTypedArray()
                response.body()?.rates?.keys?.let { keysList = it.toTypedArray() }
                response.body()?.rates?.values?.let { valuesList = it.toTypedArray() }
                Log.e("TAG", keysList.toString())
            }

            override fun onFailure(call: Call<ExchangeRates>, t: Throwable) {
                Log.e("TAG", t.message.toString())
            }

        })
//        compositeDisposable.add(webApi.getRates()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe( {
////                for (k in it.rates.keys) {
////                    keysList.add(k)
////                }
////                for (v in it.rates.values) {
////                    valuesList.add(v.toDouble())
////                }
////                _keysList.postValue(it.rates.keys.toString())
////                keysList = ArrayList(it.rates.keys)
////                valuesList = ArrayList(it.rates.values)
////                Log.e("TAG", it.rates[keysList[0]].toString())
//                _keysList.value = it
//                Log.e("TAG", _keysList.value.toString())
//                Log.e("TAG", valuesList.toString())
//            }, {
//                Log.d("TAG", it.message.toString())
//            }))
    }

    fun calculateRates(text: String, you: Int, they: Int): LiveData<Double> {
        if (text.isNotEmpty() && valuesList.isNotEmpty()) {
            _youSend.postValue(text.toDouble() * valuesList[you] * valuesList[they])
            Log.e("TAG", keysList.toString())
        }
        else
            _youSend.postValue(0.0)
        return _youSend
    }
}