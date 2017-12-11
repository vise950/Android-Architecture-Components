package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class Weather {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var latitude: Double? = null
    var longitude: Double? = null
    @Ignore
    var currently: Currently? = null

    override fun toString(): String = "lat: $latitude, log: $longitude"

}

@Entity
class Currently {
    var time: Long? = null
    var summary: String? = null
    var temperature: Double? = null
}