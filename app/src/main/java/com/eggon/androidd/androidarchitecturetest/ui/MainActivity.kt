package com.eggon.androidd.androidarchitecturetest.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import co.eggon.eggoid.extension.error
import com.eggon.androidd.androidarchitecturetest.R
import com.eggon.androidd.androidarchitecturetest.application.Init
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import com.eggon.androidd.androidarchitecturetest.util.BaseActivity
import com.eggon.androidd.androidarchitecturetest.util.Utils
import com.eggon.androidd.androidarchitecturetest.util.hideKeyboard
import com.eggon.androidd.androidarchitecturetest.viewModel.WeatherViewModel
import com.eggon.androidd.androidarchitecturetest.viewModel.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var repo: WeatherRepository

    private lateinit var snackbar: Snackbar

    private var lat: Double? = 20.0
    private var lng: Double? = 20.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Init).appComponent.inject(this)

        snackbar = Utils.makeSnackbar(this, root_layout)

        viewModel = this.viewModel { WeatherViewModel(repo, disposables) }
        observeData()

        load_btn.setOnClickListener {
            this.hideKeyboard()
            snackbar.show()

            lat = latitude.text.toString().toDouble()
            lng = longitude.text.toString().toDouble()

            Handler().postDelayed({
                if (lat != null && lng != null) {
                    viewModel.updateWeather(Pair(lat!!, lng!!))
                }
            }, 2000)
        }
    }

    override fun onStop() {
        super.onStop()
        snackbar.dismiss()
    }

    private fun observeData() {
        viewModel.weatherData.observe(this, Observer {
            snackbar.dismiss()
            "OBSERVE DATA".error()
            it?.let { handleResponse(it) }
        })
    }

    private fun handleResponse(response: Weather) {
        tv_1.text = "latitude: ${response.latitude}"
        tv_2.text = "longitude: ${response.longitude}"
        tv_3.text = "currently time: ${response.currently.time}"
        tv_4.text = "currently summary: ${response.currently.summary}"
        tv_5.text = "currently temperature: ${response.currently.temperature}"

        var s = ""
        response.daily.data.forEach {
            s += "- ${it.summary}, ${it.temperatureMax}° \n"
        }
        tv_6.text = s
    }
}