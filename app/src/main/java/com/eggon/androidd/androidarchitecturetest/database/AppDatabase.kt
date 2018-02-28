package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.DailyData
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Database(entities = [(Weather::class), (DailyData::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}

//todo  A migration from 1 to 2 is necessary. Please provide a Migration in the builder or call fallbackToDestructiveMigration in the builder in which case Room will re-create all of the tables.