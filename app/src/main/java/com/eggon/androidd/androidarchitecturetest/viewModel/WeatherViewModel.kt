package com.eggon.androidd.androidarchitecturetest.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository,
                                           coordinates: Pair<Double, Double>?) : ViewModel() {

    private val data: MediatorLiveData<Weather>? = MediatorLiveData()

    init {
        data?.addSource(weatherRepository.getWeather(coordinates?.first, coordinates?.second),
                { data.value = it })
    }

    fun updateWeather(coordinates: Pair<Double?, Double?>) {
        weatherRepository.getWeather(coordinates.first, coordinates.second)
    }

    fun getWeather(): LiveData<Weather>? = data
}