package com.eggon.androidd.androidarchitecturetest.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.isConnectionAvailable
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.local.WeatherLocalRepository
import com.eggon.androidd.androidarchitecturetest.repository.remote.WeatherRemoteRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val context: Context, private val localRepo: WeatherLocalRepository,
                                            private val remoteRepo: WeatherRemoteRepository) {

    fun getWeather(lat: Double?, lng: Double?): LiveData<Weather>? {
        if (context.isConnectionAvailable()) {
            remoteRepo.getData(lat, lng, {
                it.error()
            })
        }
        return localRepo.getData()
    }
}