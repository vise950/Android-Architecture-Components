package com.eggon.androidd.androidarchitecturetest.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import co.eggon.eggoid.extension.isConnectionAvailable
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.local.WeatherLocalRepository
import com.eggon.androidd.androidarchitecturetest.repository.remote.WeatherRemoteRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val context: Context,
                                            private val localRepo: WeatherLocalRepository,
                                            private val remoteRepo: WeatherRemoteRepository) {

    fun getWeather(disposable: CompositeDisposable, lat: Double?, lng: Double?): LiveData<Weather> {
        if (context.isConnectionAvailable()) {
            remoteRepo.getData(disposable, lat, lng)
        }
        return localRepo.getData()
    }
}