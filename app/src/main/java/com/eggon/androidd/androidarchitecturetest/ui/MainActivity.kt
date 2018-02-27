package com.eggon.androidd.androidarchitecturetest.ui

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import co.eggon.eggoid.extension.error
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.util.BaseActivity
import com.eggon.androidd.androidarchitecturetest.viewModel.WeatherViewModel
import com.eggon.androidd.androidarchitecturetest.viewModel.viewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var pd: ProgressDialog

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pd = ProgressDialog(this)

        viewModel = this.viewModel { WeatherViewModel(this, disposables) }

        observeData()

        val w = Weather()

        load_btn.setOnClickListener {
            pd.show()

            val lat = if (latitude.text.toString().isBlank()) null else latitude.text.toString().toDouble()
            val lng = if (longitude.text.toString().isBlank()) null else longitude.text.toString().toDouble()

            Handler().postDelayed({
                viewModel.updateWeather(lat, lng)
            }, 2000)
        }
    }

    private fun observeData() {
        viewModel.getWeather()?.observe(this, Observer {
            "observe data".error()
            pd.dismiss()
            it?.let { handleResponse(it.toString()) }
        })
    }

    private fun handleResponse(response: String?) {
        result_tv.text = response
    }
}