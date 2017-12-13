package com.eggon.androidd.androidarchitecturetest.repository.remote

import co.eggon.eggoid.ServiceFactory
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.network
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.retofit.WebService

class WeatherRemoteRepository {

    fun getData(lat: Double?, lng: Double?, onDataReady: (Weather) -> Unit, onError: (Throwable) -> Unit) {
        ServiceFactory().with(WebService::class)
                .getWeather(lat ?: 0.0, lng ?: 0.0)
                .network({
                    onDataReady.invoke(it)
                }, {
                    it.error("call error")
                    onError.invoke(it)
                })
    }
}