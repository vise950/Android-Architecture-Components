package com.eggon.androidd.androidarchitecturetest.model

data class Currently(
        var time: Long? = null,
        var summary: String? = null,
        var temperature: Double? = null
)