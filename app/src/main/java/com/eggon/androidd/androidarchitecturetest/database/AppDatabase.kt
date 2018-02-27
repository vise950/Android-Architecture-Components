package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Database(entities = [(Weather::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var database: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
                database ?: synchronized(this) {
                    database ?: createDatabase(context).also { database = it }
                }

        private fun createDatabase(context: Context): AppDatabase =
                Room.databaseBuilder(context, AppDatabase::class.java, "my_database").build()
    }

    abstract fun weatherDao(): WeatherDao
}