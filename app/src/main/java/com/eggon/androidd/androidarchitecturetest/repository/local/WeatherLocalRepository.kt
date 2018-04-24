package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import co.eggon.eggoid.extension.error
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.DailyData
import com.eggon.androidd.androidarchitecturetest.model.Weather
import javax.inject.Inject

class WeatherLocalRepository @Inject constructor(private val dao: WeatherDao) {

    fun getData(lat: Double, lng: Double): LiveData<Weather> {
        val data = dao.getData(lat, lng)
        data.value.toString().error("GET LOCAL DATA")
        return data
    }

    fun getDailyData(weather: Weather): LiveData<List<DailyData>> = dao.getDailyData(weather.latitude, weather.longitude)
}