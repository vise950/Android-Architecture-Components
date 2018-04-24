package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index

@Entity(tableName = "daily_data",
        primaryKeys = ["latitude", "longitude", "time"],
        foreignKeys = [ForeignKey(entity = Weather::class,
                parentColumns = ["latitude", "longitude"],
                childColumns = ["latitude", "longitude"])],
        indices = [Index("latitude"), Index("longitude")])
data class DailyData(
        var latitude: Double,
        var longitude: Double,
        var time: Long,
        var summary: String,
        var temperatureMax: Double
)