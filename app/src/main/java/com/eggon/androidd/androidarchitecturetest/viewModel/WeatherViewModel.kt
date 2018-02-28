package com.eggon.androidd.androidarchitecturetest.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository,
                                           private val disposable: CompositeDisposable,
                                           private var coordinates: Pair<Double?, Double?>?) : ViewModel() {

    private val weatherData: MediatorLiveData<Weather> = MediatorLiveData()

    init {
        weatherData.addSource(updateWeather(),
                { weatherData.value = it })
    }

    fun updateWeather(coordinates: Pair<Double?, Double?>? = null): LiveData<Weather> {
        coordinates?.let { this.coordinates = it }
        var weatherLiveData = weatherRepository.getWeather(disposable, this.coordinates?.first
                ?: 0.0, this.coordinates?.second ?: 0.0)
        weatherLiveData = Transformations.switchMap(weatherLiveData, { weather ->
            weather?.let {
                val dailyData = weatherRepository.getDailyData(it)
                Transformations.map(dailyData, {
                    weather.daily.data = it
                    weather
                })
            }
        })
        return weatherLiveData
    }

    fun getWeather(): LiveData<Weather>? = weatherData
}