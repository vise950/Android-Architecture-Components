package com.eggon.androidd.androidarchitecturetest.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import co.eggon.eggoid.extension.error
import com.eggon.androidd.androidarchitecturetest.R
import com.eggon.androidd.androidarchitecturetest.application.Init
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import com.eggon.androidd.androidarchitecturetest.util.BaseActivity
import com.eggon.androidd.androidarchitecturetest.util.Utils
import com.eggon.androidd.androidarchitecturetest.util.hideKeyboard
import com.eggon.androidd.androidarchitecturetest.viewModel.WeatherViewModel
import com.eggon.androidd.androidarchitecturetest.viewModel.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var repo: WeatherRepository

    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Init).appComponent.inject(this)

        viewModel = this.viewModel { WeatherViewModel(repo, disposables, null) }

        snackbar = Utils.makeSnackbar(this, root_layout)


        observeData()
        load_btn.setOnClickListener {
            this.hideKeyboard()
            snackbar.show()

            val lat = if (latitude.text.toString().isBlank()) null else latitude.text.toString().toDouble()
            val lng = if (longitude.text.toString().isBlank()) null else longitude.text.toString().toDouble()

            Handler().postDelayed({
                viewModel.updateWeather(Pair(lat, lng))
            }, 2000)
        }
    }

    private fun observeData() {
        viewModel.getWeather()?.observe(this, Observer {
            snackbar.dismiss()
            it?.let { handleResponse(it.toString()) }
        })
    }

    private fun handleResponse(response: String?) {
        response.error("response")
        result_tv.text = response
    }
}