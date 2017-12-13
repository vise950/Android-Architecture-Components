package com.eggon.androidd.androidarchitecturetest.model

data class Currently(
        var currentlyId: Long = 0,
        var time: Long? = null,
        var summary: String? = null,
        var temperature: Double? = null
)