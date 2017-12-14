package com.eggon.androidd.androidarchitecturetest.repository.remote

import co.eggon.eggoid.ServiceFactory
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.network
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.retofit.WebService
import javax.inject.Inject

class WeatherRemoteRepository @Inject constructor(val dao: WeatherDao) {

    private fun count(): Int = dao.count()

    fun getData(lat: Double?, lng: Double?, onError: (Throwable) -> Unit) {
        ServiceFactory().with(WebService::class)
                .getWeather(lat ?: 0.0, lng ?: 0.0)
                .map {
                    if (count() > 0) {
                        dao.updateData(it)
                        2
                    } else {
                        dao.insertData(it)
                        1
                    }
                }
                .network({
                    when(it){
                        1 -> { "Data inserted".error("RemoteRepo") }
                        2 -> { "Data updated".error("RemoteRepo") }
                        else -> { "Unknown error".error("RemoteRepo") }
                    }
                }, {
                    it.error("call error")
                    onError.invoke(it)
                })
    }
}