package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Entity

@Entity
data class Weather(var latitude: Double? = null,
                   var longitude: Double? = null,
                   var currently: Currently? = null) {

    override fun toString(): String = "summary: ${currently?.summary}, temperature: ${currently?.temperature}"
}

@Entity
data class Currently(
        var time: Long? = null,
        var summary: String? = null,
        var temperature: Double? = null)