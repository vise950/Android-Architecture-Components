package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import co.eggon.eggoid.extension.error
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather


class WeatherLocalRepository {

    private val dao: WeatherDao? = AppDatabase.getDatabase()?.weatherDao()

    fun getData(): LiveData<Weather>? = dao?.getData()

    fun count(): Int = dao?.count() ?: -1

    fun saveData(weather: Weather) {
        count().error("num of entry")
        if (count() > 0) {
            dao?.updateData(weather)
        } else {
            dao?.insertData(weather)
        }
    }
}