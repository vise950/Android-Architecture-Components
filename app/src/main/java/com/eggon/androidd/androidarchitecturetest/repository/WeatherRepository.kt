package com.eggon.androidd.androidarchitecturetest.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import co.eggon.eggoid.extension.isConnectionAvailable
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.repository.local.WeatherLocalRepository
import com.eggon.androidd.androidarchitecturetest.repository.remote.WeatherRemoteRepository
import io.reactivex.disposables.CompositeDisposable


class WeatherRepository(private val context: Context) {

    private val db = AppDatabase.getDatabase(context)

    private val localRepo = WeatherLocalRepository(db)
    private val remoteRepo = WeatherRemoteRepository(db)

    fun getWeather(disposable: CompositeDisposable, lat: Double?, lng: Double?): LiveData<Weather>? {
        if (context.isConnectionAvailable()) {
            remoteRepo.getData(disposable, lat, lng)
        }
        return localRepo.getData()
    }

    fun getLastData(): LiveData<Weather> = localRepo.getData()
}