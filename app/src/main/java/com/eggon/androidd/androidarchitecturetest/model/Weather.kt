package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

@Entity(tableName = "weather", primaryKeys = ["latitude", "longitude"])
data class Weather(
        var latitude: Double,
        var longitude: Double,
        @Embedded(prefix = "currently_") var currently: Currently,
        @Embedded(prefix = "daily_") var daily: Daily
) {
    fun updateKeys() {
        daily.data.forEach {
            it.latitude = latitude
            it.longitude = longitude
        }
    }
}