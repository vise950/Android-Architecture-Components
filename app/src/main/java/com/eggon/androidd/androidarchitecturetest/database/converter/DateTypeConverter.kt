package com.eggon.androidd.androidarchitecturetest.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


object DateTypeConverter {

    @TypeConverter
    fun toDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun toLong(value: Date): Long {
        return value.time
    }
}