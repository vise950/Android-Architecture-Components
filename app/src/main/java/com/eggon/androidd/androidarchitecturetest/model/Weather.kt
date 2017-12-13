package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity()
class Weather(
        @PrimaryKey
        var weatherId: Long = 0,
        var latitude: Double? = null,
        var longitude: Double? = null,
        @Embedded var currently: Currently = Currently()
) {

    override fun toString(): String = "lat: $latitude, log: $longitude, summary: ${currently.summary}, temperature: ${currently.temperature}"
}