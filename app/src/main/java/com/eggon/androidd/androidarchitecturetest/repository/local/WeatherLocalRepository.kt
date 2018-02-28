package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.DailyData
import com.eggon.androidd.androidarchitecturetest.model.Weather
import javax.inject.Inject

class WeatherLocalRepository @Inject constructor(private val dao: WeatherDao) {
    fun getData(lat: Double, lng: Double): LiveData<Weather> = dao.getData(lat, lng)
    fun getDailyData(weather: Weather): LiveData<List<DailyData>> = dao.getDailyData(weather.latitude, weather.longitude, weather.timestamp)
}