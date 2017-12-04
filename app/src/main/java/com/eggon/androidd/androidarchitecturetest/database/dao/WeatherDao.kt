package com.eggon.androidd.androidarchitecturetest.database.dao

import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.safeExec
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.util.RealmLiveData
import com.eggon.androidd.androidarchitecturetest.util.asLiveData
import io.realm.Realm

class WeatherDao(private val realm: Realm) {

    fun loadData(): RealmLiveData<Weather>? = realm.where(Weather::class.java).findAll().asLiveData()

    fun insertWeather(w: Weather) {
        realm.safeExec {
            it.copyToRealmOrUpdate(w)
        } onError {
            it.error("insert error")
        }
    }
}