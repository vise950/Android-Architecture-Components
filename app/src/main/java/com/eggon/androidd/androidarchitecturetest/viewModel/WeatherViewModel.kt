package com.eggon.androidd.androidarchitecturetest.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import co.eggon.eggoid.extension.error
import co.eggon.eggoid.extension.isConnectionAvailable
import com.eggon.androidd.androidarchitecturetest.application.Init
import com.eggon.androidd.androidarchitecturetest.database.AppDatabase
import com.eggon.androidd.androidarchitecturetest.network.ApiResponse
import com.eggon.androidd.androidarchitecturetest.repository.WeatherRepository
import kotlinx.coroutines.experimental.launch

/**
 * AndroidViewModel contain application context (to retrieve the context call getApplication()),
 * otherwise use regular ViewModel
 */
class WeatherViewModel(app: Application) : AndroidViewModel(app) {


    private val data: MediatorLiveData<ApiResponse>? = MediatorLiveData()
    private val weatherRepository = WeatherRepository()
    private val db: AppDatabase? = AppDatabase.getDatabase()

    private var isConnected: Boolean? = null

    fun loadWeather() {
        isConnected = getApplication<Init>().isConnectionAvailable()
        if (isConnected == true) {
            data?.addSource(weatherRepository.loadData(), { data.value = it })
        } else {
            launch {
                db?.weatherDao()?.count().error("num of entry")
                db?.weatherDao()?.getData()?.let {
                    it.value.error("value")
                    "get data from db".error()
//                data?.value = ApiResponse(it)
                }
            }
        }
    }

    fun getWeather(): LiveData<ApiResponse>? = data
}

