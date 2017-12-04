package com.eggon.androidd.androidarchitecturetest.model

import io.realm.RealmObject

open class Weather(var latitude: Double? = null, var longitude: Double? = null, var currently: Currently? = null) : RealmObject() {

    override fun toString(): String = "summary: ${currently?.summary}, temperature: ${currently?.temperature}"
}

open class Currently(
        var time: Long? = null,
        var summary: String? = null,
        var temperature: Double? = null) : RealmObject()