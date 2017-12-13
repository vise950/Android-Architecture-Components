package com.eggon.androidd.androidarchitecturetest.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository) : ViewModel() {

    private val data: MediatorLiveData<Weather>? = MediatorLiveData()

    fun updateWeather(lat: Double?, lng: Double?) {
        weatherRepository.getWeather(lat, lng)?.let {
            data?.addSource(it, { data.value = it })
        }
    }

    fun getWeather(): LiveData<Weather>? = data
}