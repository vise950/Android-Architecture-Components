package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather


class WeatherLocalRepository {

    private val dao: WeatherDao? = AppDatabase.getDatabase()?.weatherDao()

    fun getData(): LiveData<Weather>? = dao?.getData()

    fun count(): Int = dao?.count() ?: -1

    fun saveData(weather: Weather) {
        dao?.insertData(weather)
    }
}