package com.eggon.androidd.androidarchitecturetest.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import co.eggon.eggoid.ServiceFactory
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.network
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.retofit.WebService


class DataRequest(private val lat: Double, private val lng: Double) {

    private val data = MutableLiveData<ApiResponse>()
    private val dao = AppDatabase.weatherDao()

    init {
        syncData()
    }

    private fun syncData() {
//        ServiceFactory().with(WebService::class)
//                .getWeather(lat, lng)
//                .objectToRealm(realm)
//                .onError {
//                    it.error("call error")
//                    data.value = ApiResponse(it)
//                }

        ServiceFactory().with(WebService::class)
                .getWeather(lat, lng)
                .network({
                    data.value = ApiResponse(it)
                }, {
                    it.error("call error")
                    data.value = ApiResponse(it)
                })

    }

    fun getData(): LiveData<ApiResponse> = data
}