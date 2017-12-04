package com.eggon.androidd.androidarchitecturetest.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.eggon.androidd.androidarchitecturetest.network.ApiResponse
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository

/**
 * AndroidViewModel contain application context (to retrieve the context call getApplication()),
 * otherwise use regular ViewModel
 */
class WeatherViewModel : ViewModel() {

    private val data: MediatorLiveData<ApiResponse>? = MediatorLiveData()
    private val weatherRepository = WeatherRepository()

    fun loadWeather() {
        data?.addSource(weatherRepository.loadData(), { data.value = it })
    }

    fun getWeather(): LiveData<ApiResponse>? = data
}

