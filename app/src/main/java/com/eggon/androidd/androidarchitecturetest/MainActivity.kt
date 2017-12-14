package com.eggon.androidd.androidarchitecturetest

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.eggon.androidd.androidarchitecturetest.application.Init
import com.eggon.androidd.androidarchitecturetest.viewModel.ViewModelFactory
import com.eggon.androidd.androidarchitecturetest.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var pd: ProgressDialog

    private lateinit var viewModel: WeatherViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Init).appComponent.inject(this)

        pd = ProgressDialog(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        observeData()
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
            pd.dismiss()
            it?.let { handleResponse(it.toString()) }
        })
    }

    private fun handleResponse(response: String?) {
        result_tv.text = response
    }
}