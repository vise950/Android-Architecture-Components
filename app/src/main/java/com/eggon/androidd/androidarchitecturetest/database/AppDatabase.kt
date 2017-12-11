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

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun createDatabase(context: Context) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        fun destroyDatabase() {
            INSTANCE = null
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, AppDatabase::class.java, "Sample.db")
                        .build()

        fun getDatabase(): AppDatabase? = INSTANCE
    }
}