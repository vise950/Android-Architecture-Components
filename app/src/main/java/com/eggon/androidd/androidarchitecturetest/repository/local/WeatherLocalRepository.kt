package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.model.Weather

class WeatherLocalRepository(private val db: AppDatabase) {

    fun getData(): LiveData<Weather> {
        return db.weatherDao().getData()
    }
}