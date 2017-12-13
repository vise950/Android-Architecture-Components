package com.eggon.androidd.androidarchitecturetest.repository.local

import android.arch.lifecycle.LiveData
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.model.Weather

class WeatherLocalRepository {

    //todo dagger
    //    @Inject lateinit var dao: WeatherDao

    var dao = AppDatabase.getDatabase().weatherDao()

    fun getData(): LiveData<Weather>? = dao.getData()

    fun count(): Int = dao.count()

    fun saveData(weather: Weather) {
        if (count() > 0) {
            dao.updateData(weather)
        } else {
            dao.insertData(weather)
        }
    }
}