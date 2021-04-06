package com.ramilkapev.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        var selectedRateYou = 0
        var selectedRateThey = 0

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            binding.drawer.openDrawer(Gravity.START)
        }

        mainViewModel.fetchRates((application as ConverterApp).webApi, "USD")
//        mainViewModel.

//        lateinit var selectedItems: ArrayList<String>
        lateinit var array: Array<String>
//        array = selectedItems.toArray(array)


        binding.youSendBtn.setOnClickListener {
//            selectedItems = mainViewModel.keysList
//            array = arrayOfNulls<String>(selectedItems.size)
            array = mainViewModel.keysList
//            Log.e("TAG", selectedItems.toString())
            Log.e("TAG", array.size.toString())
            MaterialAlertDialogBuilder(this)
                .setTitle("Choose a currency")
                .setNeutralButton("cancel") { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("ok") { dialog, which ->

                }
                .setSingleChoiceItems(array, -1) { dialog, which ->
                    binding.youSendBtn.text = array[which]
                    selectedRateYou = which
                    Log.e("TAG", selectedRateYou.toString())
                    mainViewModel.fetchRates((application as ConverterApp).webApi, array[selectedRateYou])
                }
                .show()
        }

        binding.theyGetBtn.setOnClickListener {
//            selectedItems = mainViewModel.keysList
//            array = arrayOfNulls(selectedItems.size)
            array = mainViewModel.keysList
            MaterialAlertDialogBuilder(this)
                .setTitle("Choose a currency")
                .setNeutralButton("cancel") { dialog, which ->
                    dialog.cancel()
                }
                .setPositiveButton("ok") { dialog, which ->

                }
                .setSingleChoiceItems(array, -1) { dialog, which ->
                    binding.theyGetBtn.text = array[which]
                    selectedRateThey = which
                    Log.e("TAG", selectedRateThey.toString())
                    mainViewModel.calculateRates(binding.youSendEt.text.toString(), selectedRateYou, selectedRateThey).observe(this) {
                        binding.theyGetEt.text = it.toString()
                    }
                    mainViewModel.fetchRates((application as ConverterApp).webApi, array[selectedRateYou])
                }
                .show()
        }

        binding.youSendEt.doOnTextChanged { text, start, before, count ->
            mainViewModel.calculateRates(binding.youSendEt.text.toString(), selectedRateYou, selectedRateThey).observe(this) {
                binding.theyGetEt.text = it.toString()
            }
        }
    }

}