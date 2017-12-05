package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Database(entities = [(Weather::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

//    companion object {
//
//        @Volatile private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//                INSTANCE ?: synchronized(this) {
//                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//                }
//
//        private fun buildDatabase(context: Context) =
//                Room.databaseBuilder(context.applicationContext,
//                        AppDatabase::class.java, "Sample.db")
//                        .build()
//    }
}