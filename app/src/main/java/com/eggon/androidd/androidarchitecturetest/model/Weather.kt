package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

@Entity(tableName = "weather", primaryKeys = ["latitude", "longitude", "timestamp"])
data class Weather(
        var weatherId: Long,
        var latitude: Double,
        var longitude: Double,
        var timestamp: Long,
        @Embedded(prefix = "currently_") var currently: Currently,
        @Embedded(prefix = "daily_") var daily: Daily
) {
    fun updateKeys() {
        timestamp = currently.time
        daily.data.forEach {
            it.latitude = latitude
            it.longitude = longitude
            it.timestamp = currently.time
        }
    }
}

/*

@Entity(tableName = "weather", primaryKeys = ["id"])
data class Weather(
        var id: Long,
        var latitude: Double,
        var longitude: Double,
        var timestamp: Long,
        @Embedded(prefix = "currently_") var currently: Currently,
        @Embedded(prefix = "daily_") var daily: Daily
)
{
    fun updateKeys(){
        timestamp = currently.time
        daily.data.forEach {
            it.latitude = latitude
            it.longitude = longitude
            it.timestamp = currently.time
        }
    }
}

* */