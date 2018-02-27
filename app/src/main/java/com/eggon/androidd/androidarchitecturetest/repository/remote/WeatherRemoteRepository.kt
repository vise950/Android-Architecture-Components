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

class WeatherRemoteRepository @Inject constructor(val dao: WeatherDao) {

    fun getData(disposable: CompositeDisposable, lat: Double?, lng: Double?) {
        ServiceFactory().with(WebService::class)
                .getWeather(lat ?: 0.0, lng ?: 0.0)
                .network(disposable, {
                    insertResultIntoDb(it)
                }, {
                    it.error("call error")
                })
    }

    private fun insertResultIntoDb(data: Weather) {
        doAsync {
            dao.insertData(data)
        }
    }
}
