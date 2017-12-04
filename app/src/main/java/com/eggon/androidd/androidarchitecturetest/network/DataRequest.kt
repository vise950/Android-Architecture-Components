package com.eggon.androidd.androidarchitecturetest.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import co.eggon.eggoid.ServiceFactory
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.network
import com.eggon.androidd.androidarchitecturetest.retofit.WebService


class DataRequest(private val lat: Double, private val lng: Double) {

//    private val realmListener = RealmChangeListener<Weather> {
//        data.value = ApiResponse(it)
//    }

    private val data = MutableLiveData<ApiResponse>()

    init {
//        attachListener()
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

//    private fun attachListener() {
//        realm?.where(Weather::class.java)
//                ?.equalTo("latitude", lat)
//                ?.equalTo("longitude", lng)
//                ?.findFirst()
//                ?.addChangeListener(realmListener)
//    }

    fun getData(): LiveData<ApiResponse> = data
}