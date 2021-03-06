package com.eggon.androidd.androidarchitecturetest.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import co.eggon.eggoid.extension.isConnectionAvailable
import com.eggon.androidd.androidarchitecturetest.model.DailyData
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.local.WeatherLocalRepository
import com.eggon.androidd.androidarchitecturetest.repository.remote.WeatherRemoteRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val context: Context,
                                            private val localRepo: WeatherLocalRepository,
                                            private val remoteRepo: WeatherRemoteRepository) {

    fun updateWeather(disposable: CompositeDisposable, lat: Double, lng: Double) {
        if (context.isConnectionAvailable()) {
            remoteRepo.getData(disposable, lat, lng)
        }
    }

    fun getData(lat: Double, lng: Double): LiveData<Weather> = localRepo.getData(lat, lng)
    fun getDailyData(weather: Weather): LiveData<List<DailyData>> = localRepo.getDailyData(weather)
}