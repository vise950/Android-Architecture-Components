package com.eggon.androidd.androidarchitecturetest.repository.remote

import co.eggon.eggoid.ServiceFactory
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.network
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.retofit.WebService
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class WeatherRemoteRepository @Inject constructor(private val dao: WeatherDao) {

    fun getData(disposable: CompositeDisposable, lat: Double, lng: Double) {
        ServiceFactory().with(WebService::class)
                .getWeather(lat, lng)
                .network(disposable, {
                    insertResultIntoDb(it)
                }, {
                    it.error("call error")
                })
    }

    private fun insertResultIntoDb(weather: Weather) {
        doAsync {
            weather.updateKeys()
            dao.insertData(weather)
            dao.insertDailyData(*weather.daily.data.toTypedArray())
            "DATA INSERTED".error()
        }
    }
}
