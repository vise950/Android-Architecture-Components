package com.eggon.androidd.androidarchitecturetest.network

import com.eggon.androidd.androidarchitecturetest.model.Weather

class ApiResponse {

    private var weather: Weather? = null
    private var error: Throwable? = null

    constructor(data: Weather) {
        weather = data
        error = null
    }

    constructor(e: Throwable?) {
        error = e
        weather = null
    }

    fun getData(): Weather? = weather
    fun getError(): Throwable? = error
}