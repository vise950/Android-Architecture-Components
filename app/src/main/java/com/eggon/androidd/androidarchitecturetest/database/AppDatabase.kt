package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Database(entities = [(Weather::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}