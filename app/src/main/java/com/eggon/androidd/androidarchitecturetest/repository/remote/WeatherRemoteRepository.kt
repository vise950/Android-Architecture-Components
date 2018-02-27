package com.eggon.androidd.androidarchitecturetest.repository.remote

import co.eggon.eggoid.ServiceFactory
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.network
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.model.Weather
import com.eggon.androidd.androidarchitecturetest.retofit.WebService
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync

class WeatherRemoteRepository(private val db: AppDatabase) {

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
            db.weatherDao().insertData(data)
        }
    }
}
