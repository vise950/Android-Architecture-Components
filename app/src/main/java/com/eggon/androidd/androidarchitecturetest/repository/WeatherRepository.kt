package com.eggon.androidd.androidarchitecturetest.repository

import android.arch.lifecycle.LiveData
import com.eggon.androidd.androidarchitecturetest.network.ApiResponse
import com.eggon.androidd.androidarchitecturetest.network.DataRequest


class WeatherRepository {
    fun loadData(): LiveData<ApiResponse> = DataRequest(37.8267, -122.4233).getData()
}