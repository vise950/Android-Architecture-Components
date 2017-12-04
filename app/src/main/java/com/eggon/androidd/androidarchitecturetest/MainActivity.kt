package com.eggon.androidd.androidarchitecturetest

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.eggon.eggoid.extension.error
import com.eggon.androidd.androidarchitecturetest.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var pd: ProgressDialog

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pd = ProgressDialog(this)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        observeData()
        load_btn.setOnClickListener {
            pd.show()
            viewModel.loadWeather()
        }
    }

    private fun observeData() {
        viewModel.getWeather()?.observe(this, Observer {
            pd.dismiss()
            it?.let {
                it.getData()?.let { handleResponse(it.toString()) }
                it.getError()?.let { handleResponse(it.toString()) }
            }?.run {
                "data null".error()
            }
        })
    }

    private fun handleResponse(response: String?) {
        result_tv.text = response
    }
}