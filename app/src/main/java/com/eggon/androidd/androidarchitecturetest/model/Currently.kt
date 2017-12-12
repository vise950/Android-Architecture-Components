package com.eggon.androidd.androidarchitecturetest.model

import android.arch.persistence.room.Entity
import android.support.annotation.NonNull

@Entity
class Currently(
        @NonNull
        var id: Long = 0,
        var time: Long? = null,
        var summary: String? = null,
        var temperature: Double? = null
)