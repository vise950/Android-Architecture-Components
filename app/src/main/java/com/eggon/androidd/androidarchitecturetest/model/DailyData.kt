package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "daily_data",
        foreignKeys = [ForeignKey(entity = Weather::class, parentColumns = ["latitude", "longitude", "timestamp"], childColumns = ["latitude", "longitude", "timestamp"])],
        indices = [Index("latitude"), Index("longitude"), (Index("timestamp"))]
        //foreignKeys = [ForeignKey(entity = Weather::class, parentColumns = ["id"], childColumns = ["id"])]
)
data class DailyData(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var latitude: Double,
        var longitude: Double,
        var timestamp: Long,
        var summary: String,
        var temperatureMax: Double
)
