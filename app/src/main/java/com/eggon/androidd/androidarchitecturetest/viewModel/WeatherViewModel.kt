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
                                           private val disposable: CompositeDisposable) : ViewModel() {

    val weatherData: MediatorLiveData<Weather> = MediatorLiveData()

    init {
        weatherData.addSource(getWeather(),
                {
                    weatherData.value = it
                })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun updateWeather(coordinates: Pair<Double, Double>) {
        weatherRepository.updateWeather(disposable, coordinates.first, coordinates.second)
    }

    private fun getWeather(coordinates: Pair<Double?, Double?>? = null): LiveData<Weather> {
        return if (coordinates?.first != null && coordinates.second != null) {
            Transformations.switchMap(weatherRepository.getData(coordinates.first!!, coordinates.second!!), { data ->
                Transformations.map(weatherRepository.getDailyData(data), {
                    data.daily.data = it
                    data
                })
            })
        } else {
            MediatorLiveData()
        }
    }
}