package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather
import javax.inject.Inject

class WeatherLocalRepository @Inject constructor(val dao: WeatherDao) {
    fun getData(): LiveData<Weather>? = dao.getData()
}