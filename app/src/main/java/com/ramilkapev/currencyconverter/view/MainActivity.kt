package com.ramilkapev.currencyconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ramilkapev.currencyconverter.ConverterApp
import com.ramilkapev.currencyconverter.databinding.ActivityMainBinding
import com.ramilkapev.currencyconverter.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = MainViewModel()

        lateinit var arrayYouSend: Array<String>
        lateinit var arrayTheyGet: Array<String>
        var selectedRateYouSend = 0
        var selectedRateTheyGet = 0

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            binding.drawer.openDrawer(Gravity.START)
        }

        mainViewModel.fetchRates((application as ConverterApp).webApi, "USD")

        binding.youSendBtn.setOnClickListener {
            arrayYouSend = mainViewModel.keysList
            Log.e("TAG", arrayYouSend.size.toString())
            MaterialAlertDialogBuilder(this)
                .setTitle("Choose a currency")
                .setNeutralButton("cancel") { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("ok") { dialog, which ->

                }
                .setSingleChoiceItems(arrayYouSend, -1) { dialog, which ->
                    selectedRateYouSend = which
                    binding.youSendBtn.text = arrayYouSend[selectedRateYouSend]
                    Log.e("TAG", selectedRateYouSend.toString())
                    mainViewModel.fetchRates(
                        (application as ConverterApp).webApi,
                        arrayYouSend[selectedRateYouSend]
                    )
                }
                .show()
        }

        binding.theyGetBtn.setOnClickListener {
            arrayTheyGet = mainViewModel.keysList
            MaterialAlertDialogBuilder(this)
                .setTitle("Choose a currency")
                .setNeutralButton("cancel") { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("ok") { dialog, which ->

                }
                .setSingleChoiceItems(arrayTheyGet, -1) { dialog, which ->
                    selectedRateTheyGet = which
                    binding.theyGetBtn.text = arrayTheyGet[selectedRateTheyGet]
                    Log.e("TAG", selectedRateTheyGet.toString())
                    mainViewModel.calculateRates(
                        binding.youSendEt.text.toString(),
                        selectedRateTheyGet
                    ).observe(this) {
                        binding.theyGetEt.text = String.format("%.2f", it)
                    }
                }
                .show()
        }

        // Попытка реализовать swap button. Работает, но есть проблемы

//        binding.swapBtn.setOnClickListener {
//            var asd = 0
//            asd = selectedRateYou
//            selectedRateYou = selectedRateThey
//            selectedRateThey = asd
//            mainViewModel.fetchRates((application as ConverterApp).webApi, arrayYour[selectedRateYou])
//            mainViewModel.calculateRates(binding.youSendEt.text.toString(), selectedRateThey).observe(this) {
//                binding.theyGetEt.text = String.format("%.2f", it)
//            }
//        }

        binding.youSendEt.doOnTextChanged { text, start, before, count ->
            mainViewModel.calculateRates(binding.youSendEt.text.toString(), selectedRateTheyGet)
                .observe(this) {
                    binding.theyGetEt.text = String.format("%.2f", it)
                }
        }
    }

}