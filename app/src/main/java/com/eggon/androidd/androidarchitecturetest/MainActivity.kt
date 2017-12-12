package com.eggon.androidd.androidarchitecturetest

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.eggon.eggoid.extension.error
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
            viewModel.updateWeather(latitude.text.toString().toDouble(), longitude.text.toString().toDouble())
        }
    }

    private fun observeData() {
        "observe data".error()
        viewModel.getWeather()?.observe(this, Observer {
            pd.dismiss()
            it?.let {
                handleResponse(it.toString())
            } ?: run {
                "data null".error()
            }
        })
    }

    private fun handleResponse(response: String?) {
        result_tv.text = response
    }
}