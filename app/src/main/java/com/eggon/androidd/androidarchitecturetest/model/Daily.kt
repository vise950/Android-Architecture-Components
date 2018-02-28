package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Ignore

data class Daily(
        var summary: String = "",
        @Ignore
        var data: List<DailyData> = listOf()
)