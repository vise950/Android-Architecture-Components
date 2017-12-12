package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

//@Entity(indices = [(Index("id")), (Index("currently_id"))], primaryKeys = ["id", "currently_id"])
@Entity()
class Weather {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var latitude: Double? = null
    var longitude: Double? = null
    @Embedded(prefix = "currently_")
    @NonNull
    @Ignore
    var currently: Currently = Currently()

    override fun toString(): String = "lat: $latitude, log: $longitude, summary: ${currently.summary}, temperature: ${currently.temperature}"

}