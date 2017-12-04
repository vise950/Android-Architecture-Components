package com.eggon.androidd.androidarchitecturetest.retofit

import com.eggon.androidd.androidarchitecturetest.model.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("forecast/316c6b7ea03ca2ffdbb221d553878480/{latitude},{longitude}?exclude=minutely,hourly,daily,alerts,flags")
    fun getWeather(@Path("latitude") latitude: Double,
                   @Path("longitude") longitude: Double)
            : Observable<Weather>
}