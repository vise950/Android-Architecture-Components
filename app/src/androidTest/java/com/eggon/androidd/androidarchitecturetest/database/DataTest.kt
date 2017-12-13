package com.eggon.androidd.androidarchitecturetest.database

import com.eggon.androidd.androidarchitecturetest.model.Currently
import com.eggon.androidd.androidarchitecturetest.model.Weather

class TestData {

    companion object {
        val WEATHER_ENTITY = Weather(99, 26.63, 36.489, Currently(55, 69844, "test_1", 22.6))
    }
}