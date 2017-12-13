package com.eggon.androidd.androidarchitecturetest.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.eggon.androidd.androidarchitecturetest.database.dao.WeatherDao
import com.eggon.androidd.androidarchitecturetest.model.Weather

@Database(entities = [(Weather::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    //todo dagger
    companion object {

        lateinit var INSTANCE: AppDatabase

        fun createDatabase(context: Context) {
            buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java, "my_database")
                        .fallbackToDestructiveMigration()
                        .build()

        fun getDatabase(): AppDatabase = INSTANCE
    }
}