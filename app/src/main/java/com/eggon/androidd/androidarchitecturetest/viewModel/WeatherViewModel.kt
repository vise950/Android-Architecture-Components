package com.eggon.androidd.androidarchitecturetest.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable

class WeatherViewModel(context: Context, private val disposable: CompositeDisposable) : ViewModel() {

    private val weatherRepo = WeatherRepository(context)

    private val data: MediatorLiveData<Weather>? = MediatorLiveData()

    init {
        data?.addSource(weatherRepo.getLastData(), {
            it?.let { data.value = it }
        })
    }

    fun updateWeather(lat: Double?, lng: Double?) {
        weatherRepo.getWeather(disposable, lat, lng)
    }

    fun getWeather(): LiveData<Weather>? = data
}