package com.ramilkapev.currencyconverter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramilkapev.currencyconverter.data.repository.WebApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val _youSend = MutableLiveData<Double>()
    val youSend: LiveData<Double> = _youSend

    var keysList = arrayOf<String>()
    var valuesList = arrayOf<Double>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchRates(webApi: WebApi, baseRate: String) {
        compositeDisposable.add(
            webApi.getRates(baseRate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ rates ->
                    rates.rates.keys.let { keysList = it.toTypedArray() }
                    rates.rates.values.let { valuesList = it.toTypedArray() }
                }, {
                    Log.e("TAG", it.message.toString())
                })
        )
    }

    fun calculateRates(text: String, theyGet: Int): LiveData<Double> {
        if (text.isNotEmpty() && valuesList.isNotEmpty()) {
            _youSend.postValue(text.toDouble() * valuesList[theyGet])
        } else
            _youSend.postValue(0.0)
        return _youSend
    }
}